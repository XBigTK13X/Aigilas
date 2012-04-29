using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using SPX.Entities;
using SPX.Core;

namespace OGUR.Strategies
{
    class StraightLineStrategy:IStrategy
    {
        public StraightLineStrategy(ICreature parent, params int[] targetTypes)
            : base(parent)
        {
            foreach (var targetType in targetTypes)
            {
                _targets.AddTargetTypes(targetType);
            }
        }

        public override void Act()
        {
            Console.WriteLine(_parent.Get(StatType.MOVE_COOL_DOWN));
            _parent.MoveIfPossible(0, 1);
        }
    }
}
