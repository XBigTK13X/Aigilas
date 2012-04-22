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

        private Point2 _transfer = new Point2(0, 0);
        public override void Act()
        {
            _parent.Move(0, 1);
        }
    }
}
