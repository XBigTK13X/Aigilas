using OGUR.Collision;
using OGUR.Strategies;
using OGUR.Classes;
using OGUR.Skills;
using OGUR.Sprites;
using System.Collections.Generic;
using OGUR.GameObjects;

namespace OGUR.Creatures
{
    public class AbstractCreature:ICreature
    {
        public AbstractCreature(CreatureType type,SpriteType spriteType = SpriteType.CREATURE,CreatureClass cClass = null) 
        {
            if (cClass == null)
            {
                cClass = new NoClass();
            }
            m_class = cClass;
            m_creatureType = type;
            m_baseStats = new Stats(3, 1, 1, 1, 1, 1, 1, 1, 1);
            m_maxStats = new Stats(m_baseStats);
        }
        public void Setup(Point2 position)
        {
            Setup(position, m_creatureType, m_baseStats, m_class);
            if (m_strategy == null)
            {
                m_strategy = new AttackPlayers(this);
            }
        }
        protected void Add(string skillId)
        {
            if (m_skills == null)
            {
                m_skills = new SkillPool(this);
            }
            m_skills.Add(skillId);
        }
        protected void Strengths(params StatType[] stats)
        {
            foreach (StatType stat in stats)
            {
                if (stat == StatType.MOVE_COOL_DOWN)
                    InitStat(stat, Get(stat) - 2);
                else
                    InitStat(stat, Get(stat) * 2);
            }
        }
        protected void Weaknesses(params StatType[] stats)
        {
            foreach (StatType stat in stats)
            {
                if (stat == StatType.MOVE_COOL_DOWN)
                    InitStat(stat, Get(stat) + 2);
                else
                    InitStat(stat, Get(stat) * .5f);
            }
        }
        protected void Compose(params Elements[] elems)
        {
            m_composition.AddRange(elems);
        }
    }
}
