using OGUR.Collision;
using OGUR.Strategies;
using OGUR.Classes;
using OGUR.Skills;
using OGUR.Sprites;

namespace OGUR.Creatures
{
    public class AbstractCreature:ICreature
    {
        public AbstractCreature(CreatureType type,Stats stats,SpriteType spriteType = SpriteType.CREATURE,CreatureClass cClass = null) 
        {
            if (cClass == null)
            {
                cClass = new NoClass();
            }
            m_class = cClass;
            m_creatureType = type;
            m_baseStats = stats;
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
    }
}
