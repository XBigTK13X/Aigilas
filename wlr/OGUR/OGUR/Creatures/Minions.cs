using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using OGUR.Sprites;
using OGUR.Strategies;

namespace OGUR.Creatures
{
    class Minion : ICreature
    {
        public Minion(CreatureType type = CreatureType.MINION,float coolDown = Stats.DefaultCoolDown)
        {
            m_creatureType = type;
            m_baseStats = new Stats(100f,999f,0f,0f,0f,0f,0f,0f,0f,Stats.DefaultMoveSpeed,coolDown);
        }
        public void Init(ICreature source)
        {
            m_master = source;
            Setup(source.GetLocation(), m_creatureType, m_baseStats);
            SetSkillVector(new Collision.Point2(1, 0));
            m_strategy = new MinionRotate(this);
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
            : base(CreatureType.MINION,1000f)
        {
            Add(SkillId.ACID_DRIP);
        }
    }
}
