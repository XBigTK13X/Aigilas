using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using OGUR.Sprites;

namespace OGUR.Creatures
{
    class Minion : ICreature
    {
        public Minion(CreatureType type = CreatureType.MINION,float coolDown = Stats.DefaultCoolDown)
        {
            m_creatureType = type;
            m_baseStats = new Stats(100f,999f,0f,0f,0f,0f,0f,0f,0f,coolDown);
        }
        public void Init(ICreature source)
        {
            m_master = source;
            Setup(source.GetLocation(), m_creatureType, m_baseStats);
            SetSkillVector(new Collision.Point2(1, 0));
        }
        protected void Add(string skill)
        {
            if (m_skills == null)
            {
                m_skills = new SkillPool(this);
            }
            m_skills.Add(skill);
        }

        public override void Update()
        {
            Decay();
            base.Update();
        }

        protected void Decay()
        {
            if (Get(StatType.MOVE_COOL_DOWN) <= 0)
            {
                ApplyDamage(10,null,false);
                m_skills.UseActive();
                Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));
            }
        }
    }

    class AcidNozzle : Minion
    {
        public AcidNozzle()
            : base()
        {
            Add(SkillId.ACID_DRIP);
        }
    }
}
