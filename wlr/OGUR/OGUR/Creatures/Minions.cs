using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using SPX.Sprites;
using OGUR.Strategies;
using OGUR.Entities;
using SPX.Entities;

namespace OGUR.Creatures
{
    class Minion : ICreature
    {
        public Minion(int creatureType = CreatureType.MINION,float coolDown = Stats.DefaultCoolDown)
        {
            m_creatureType = creatureType;
            m_baseStats = new Stats(80f,999f,0f,0f,0f,0f,0f,0f,0f,coolDown);
        }
        public void Init(ICreature source,SkillEffect effectGraphic)
        {
            m_master = source;
            Setup(source.GetLocation(), m_creatureType, m_baseStats,null,false);
            if (null != effectGraphic)
            {
                SetSkillVector(effectGraphic.GetDirection().Rotate180());
            }
            else
            {
                SetSkillVector(new Point2(0, 1));
            }
            m_strategy.AddTargets(m_master);
        }
        protected void Add(string skill)
        {
            if (m_skills == null)
            {
                m_skills = new SkillPool(this);
            }
            m_skills.Add(skill);
        }
    }

    class AcidNozzle : Minion
    {
        public AcidNozzle()
            : base(CreatureType.MINION,50f)
        {
            Add(SkillId.ACID_DRIP);
            m_composition.Add(Elements.EARTH);
            m_strategy = new MinionRotate(this);
        }
    }

    class DartTrap : Minion
    {
        public DartTrap()
            : base(CreatureType.MINION)
        {
            m_strategy = new MinionFire(this);
            Add(SkillId.DART);
            m_composition.Add(Elements.DARK);
        }
    }

    class Explosion : Minion
    {
        public Explosion()
            : base(CreatureType.MINION)
        {
            m_strategy = new MinionExplode(this);
            Add(SkillId.EXPLODE);
            m_composition.Add(Elements.FIRE);
        }
    }

    class VaporCloud : Minion
    {
        private ICreature m_host = null;
        public VaporCloud()
            : base(CreatureType.MINION)
        {
            m_strategy = new MinionCloud(this);
            Add(SkillId.VAPOR_CLOUD);
            m_composition.Add(Elements.WATER);
        }
        public override void Update()
        {
            base.Update();
            if (m_host == null)
            {
                foreach (var creature in EntityManager.GetCreaturesAt(m_location))
                {
                    if (creature != this)
                    {
                        m_host = creature;
                    }
                }
                if (m_host == null)
                {
                    SetInactive();
                }
            }
            if (m_host != null)
            {
                SetLocation(m_host.GetLocation());
            }
        }
    }
}
