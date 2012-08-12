package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.strategies.*;import com.aigilas.entities.*;import com.spx.entities.*;import com.spx.core.*;
    public class Minion  extends  ICreature
    {
        public Minion(int actorType = AigilasActorType.MINION,float coolDown = Stats.DefaultCoolDown)
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
            _strategy.addTargets(_master);
        }
        protected void Add(String skill)
        {
            if (_skills == null)
            {
                _skills = new SkillPool(this);
            }
            _skills.add(skill);
        }
    }

    class AcidNozzle  extends  Minion
    {
        public AcidNozzle()
            { super(AigilasActorType.MINION,50f);            Add(SkillId.ACID_DRIP);
            _composition.add(Elements.EARTH);
            _strategy = StrategyFactory.Create(Strategy.MinionRotate,this);
        }
    }

    class DartTrap  extends  Minion
    {
        public DartTrap()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionFire, this);
            Add(SkillId.DART);
            _composition.add(Elements.DARK);
        }
    }

    class Explosion  extends  Minion
    {
        public Explosion()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.EXPLODE);
            _composition.add(Elements.FIRE);
        }
    }

    public class IceShard  extends  Minion
    {
        public IceShard()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.ICE_SHARD);
            _composition.add(Elements.WATER);
        }
    }

    class PoisonCloud  extends  Minion
    {
        public PoisonCloud()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.POISON_CLOUD);
            _composition.add(Elements.MENTAL);
        }
    }

    class VaporCloud  extends  Minion
    {
        private ICreature _host = null;
        public VaporCloud()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionCloud, this);
            Add(SkillId.VAPOR_CLOUD);
            _composition.add(Elements.WATER);
        }
@Override        public  void Update()
        {
            super.Update();
            if (_host == null)
            {
                for (var creature:EntityManager.GetActorsAt(_location))
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
