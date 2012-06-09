using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Agilas.Skills;
using SPX.Sprites;
using Agilas.Strategies;
using Agilas.Entities;
using SPX.Entities;
using SPX.Core;

namespace Agilas.Creatures
{
    public class Minion : ICreature
    {
        public Minion(int actorType = AgilasActorType.MINION,float coolDown = Stats.DefaultCoolDown)
        {
            _actorType = actorType;
            _baseStats = new Stats(80f,999f,0f,0f,0f,0f,0f,0f,0f,coolDown);
        }
        public void Init(ICreature source,SkillEffect effectGraphic)
        {
            _master = source;
            Setup(source.GetLocation(), _actorType, _baseStats,null,false);
            if (null != effectGraphic)
            {
                SetSkillVector(effectGraphic.GetDirection().Rotate180());
            }
            else
            {
                SetSkillVector(new Point2(0, 1));
            }
            _strategy.AddTargets(_master);
        }
        protected void Add(string skill)
        {
            if (_skills == null)
            {
                _skills = new SkillPool(this);
            }
            _skills.Add(skill);
        }
    }

    class AcidNozzle : Minion
    {
        public AcidNozzle()
            : base(AgilasActorType.MINION,50f)
        {
            Add(SkillId.ACID_DRIP);
            _composition.Add(Elements.EARTH);
            _strategy = StrategyFactory.Create(Strategy.MinionRotate,this);
        }
    }

    class DartTrap : Minion
    {
        public DartTrap()
            : base(AgilasActorType.MINION)
        {
            _strategy = StrategyFactory.Create(Strategy.MinionFire, this);
            Add(SkillId.DART);
            _composition.Add(Elements.DARK);
        }
    }

    class Explosion : Minion
    {
        public Explosion()
            : base(AgilasActorType.MINION)
        {
            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.EXPLODE);
            _composition.Add(Elements.FIRE);
        }
    }

    public class IceShard : Minion
    {
        public IceShard()
            : base(AgilasActorType.MINION)
        {
            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.ICE_SHARD);
            _composition.Add(Elements.WATER);
        }
    }

    class PoisonCloud : Minion
    {
        public PoisonCloud()
            : base(AgilasActorType.MINION)
        {
            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.POISON_CLOUD);
            _composition.Add(Elements.MENTAL);
        }
    }

    class VaporCloud : Minion
    {
        private ICreature _host = null;
        public VaporCloud()
            : base(AgilasActorType.MINION)
        {
            _strategy = StrategyFactory.Create(Strategy.MinionCloud, this);
            Add(SkillId.VAPOR_CLOUD);
            _composition.Add(Elements.WATER);
        }
        public override void Update()
        {
            base.Update();
            if (_host == null)
            {
                foreach (var creature in EntityManager.GetActorsAt(_location))
                {
                    if (creature != this)
                    {
                        _host = creature as ICreature;
                    }
                }
                if (_host == null)
                {
                    SetInactive();
                }
            }
            if (_host != null)
            {
                SetLocation(_host.GetLocation());
            }
        }
    }
}
