using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
{
    internal abstract class IItem : GameplayObject
    {
        public decimal Weight { get; set; }
        public List<decimal> Modifers = new List<decimal>();

        protected IItem()
        {
            foreach (StatType stat in Enum.GetValues(typeof (StatType)).Cast<StatType>())
            {
                Modifers.Add(0);
            }
        }
    }
}