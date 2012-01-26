using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using OGUR.Sprites;
using OGUR.Strategies;
using OGUR.GameObjects;
using OGUR.Collision;

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
                SetSkillVector(effectGraphic.GetDirection().RotateClockwise().RotateClockwise().RotateClockwise().RotateClockwise());
            }
            else
            {
                SetSkillVector(new Point2(0, 1));
            }
            m_strategy = new MinionFire(this);
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
}
