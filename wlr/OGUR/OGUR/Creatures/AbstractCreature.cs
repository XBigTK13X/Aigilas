using OGUR.Strategies;
using OGUR.Classes;
using OGUR.Skills;
using SPX.Sprites;
using SPX.Core;

namespace OGUR.Creatures
{
    public class AbstractCreature:ICreature
    {
        public AbstractCreature(int actorType,int spriteType = SpriteType.CREATURE,CreatureClass cClass = null) 
        {
            SetClass(cClass);
            m_actorType = actorType;
            m_baseStats = new Stats(3, 1, 1, 1, 1, 1, 1, 1, 1);
            m_maxStats = new Stats(m_baseStats);
        }
        public void Setup(Point2 position)
        {
            Setup(position, m_actorType, m_baseStats, m_class);
            if (m_strategy == null)
            {
                m_strategy = new AttackStrategy(this,OgurActorType.PLAYER);
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
        protected void Strengths(params string[] stats)
        {
            foreach (string stat in stats)
            {
                InitStat(stat, Get(stat) * 2);
            }
        }
        protected void Weaknesses(params string[] stats)
        {
            foreach (string stat in stats)
            {
                InitStat(stat, Get(stat) * .5f);
            }
        }
        protected void Compose(params int[] elems)
        {
            m_composition.AddRange(elems);
        }
    }
}
