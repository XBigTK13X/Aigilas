using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Skills;

namespace OGUR.Classes
{
    public abstract class CreatureClass
    {
        private Stats m_stats;

        protected Dictionary<int, ISkill> m_skillUnlocks = new Dictionary<int, ISkill>(); 

        protected CreatureClass()
        {
            
        }
        protected CreatureClass(Stats stats)
        {
            m_stats = new Stats(stats);
        }
        public decimal GetBonus(int level,StatType stat)
        {
            return m_stats.GetBonus(level,stat);
        }
        public List<ISkill> GetLevelSkills(int level)
        {
            return (from skill in m_skillUnlocks where skill.Key <= level select skill.Value).ToList();
        }
    }
    class Brute:CreatureClass
    {
        public Brute():base(new Stats(10,1,3,1,5,1,0,0,0,0,0))
        {
            
        }
    }
    
    class Mage:CreatureClass
    {
        public Mage()
            : base(new Stats(new Stats(5, 10, 1, 5, 3, 2, 1, 0, 0, 0, 0)))
        {
            m_skillUnlocks.Add(1,new FireballSkill());
        }
    }

    class Adjuster:CreatureClass
    {
        public Adjuster() : base(new Stats(5, 10, 1, 5, 3, 2, 1, 0, 0, 0, 0))
        {
        }
    }

    class Dynamo:CreatureClass
    {
        public Dynamo():base(new Stats(5,10,1,5,3,2,1,0,0,0,0))
        {
        }
    }
    class NoClass:CreatureClass
    {
        public NoClass():base(new Stats(0,0,0,0,0,0,0,0,0,0,0))
        {
        }
    }
}
