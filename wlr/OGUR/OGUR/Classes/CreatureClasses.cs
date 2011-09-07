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
        private readonly Stats m_stats;
        protected List<KeyValuePair<int, string>> m_skillUnlocks = new List<KeyValuePair<int, string>>();

        protected CreatureClass(){}
        protected CreatureClass(Stats stats){m_stats = new Stats(stats);}
        public float GetBonus(int level,StatType stat){return m_stats.GetBonus(level,stat);}
        public List<string> GetLevelSkills(int level){return (from skill in m_skillUnlocks where skill.Key <= level select skill.Value).ToList();}
        protected void Add(int level, string skillId){m_skillUnlocks.Add(new KeyValuePair<int, string>(level,skillId));}
    }
    class NoClass : CreatureClass
    {
        public NoClass()
            : base(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        {
        }
    }
    class Brute:CreatureClass
    {
        public Brute():base(new Stats(10,1,3,1,5,1,0,0,0,0,0))
        {
            m_skillUnlocks.Add(new KeyValuePair<int, string>(1, SkillId.THRASH));
        }
    }
    class Mage:CreatureClass
    {
        public Mage(): base(new Stats(new Stats(5, 10, 1, 5, 3, 2, 1, 0, 0, 0, 0)))
        {
            Add(1, SkillId.FIREBALL);
            Add(1, SkillId.BULK);
            Add(1, SkillId.THRASH);
        }
    }
    class SlothAcolyte:CreatureClass
    {
        public SlothAcolyte(): base(new Stats(new Stats(5, 10, 1, 5, 3, 2, 1, 0, 0, 0, 0)))
        {
            Add(1, SkillId.FLOOR_SPIKES);
            Add(1, SkillId.REMOTE_MINE);
            Add(1, SkillId.ACID_NOZZLE);
            Add(1, SkillId.VAPOR_IMPLANT);
            Add(1, SkillId.DART);
        }
    }
            
}
