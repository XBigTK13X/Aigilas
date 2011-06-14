using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR
{
    abstract class IItem:GameplayObject
    {
        public decimal Weight { get; set; }
        public List<decimal> Modifers = new List<decimal>();
        protected IItem()
        {
            foreach (Stat stat in Enum.GetValues(typeof(Stat)).Cast<Stat>())
            {
                Modifers.Add(0);
            }
        }
    }
}
