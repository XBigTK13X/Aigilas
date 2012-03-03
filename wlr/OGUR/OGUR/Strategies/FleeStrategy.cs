using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using SPX.Entities;
using SPX.Core;

namespace OGUR.Strategies
{
    class FleeStrategy:IStrategy
    {
        public FleeStrategy(ICreature parent, params int[] targetTypes)
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
            if (AbleToMove())
            {
                _transfer = targetPath.GetNextMove();
                if(_transfer != null)
                {
                    nextMove.Copy(_parent.GetLocation().Add(_transfer.Minus(_parent.GetLocation()).Rotate180()));
                    _parent.MoveTo(nextMove);
                }
            }
        }
    }
}
