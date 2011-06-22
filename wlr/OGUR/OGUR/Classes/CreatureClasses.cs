using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Classes
{
    public abstract class CreatureClass
    {
        private Stats m_stats; 
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
    }
    class Brute:CreatureClass
    {
        public Brute():base(new Stats(10,1,3,1,5,1,0,0,0,0,0))
        {
        }
    }
    
    class Aduster:CreatureClass
    {
        public Aduster() : base(new Stats(5, 10, 1, 5, 3, 2, 1, 0, 0, 0, 0))
        {
        }
    }

    class Dynamo:CreatureClass
    {
        public Dynamo():base(new Stats(5,10,1,5,3,2,1,0,0,0,0))
        {
        }
    }
    class Plain:CreatureClass
    {
        public Plain():base(new Stats(0,0,0,0,0,0,0,0,0,0,0))
        {
        }
    }
}
