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
            _parent.MoveIfPossible(0, 1);
            if(_parent.GetLocation().GridY >= GameManager.TileMapHeight-1)
            {
                _parent.SetInactive();
            }
        }
    }
}
