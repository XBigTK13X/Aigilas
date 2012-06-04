using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using SPX.Entities;
using SPX.Core;

namespace OGUR.Strategies
{
    [Serializable]
    class StraightLineRotateStrategy:IStrategy
    {
        private Point2 _direction = new Point2(0, 1);

        public StraightLineRotateStrategy(ICreature parent, params int[] targetTypes)
            : base(parent,Strategy.StraightLineRotate)
        {
            foreach (var targetType in targetTypes)
            {
                _targets.AddTargetTypes(targetType);
            }
        }

        private Point2 target = new Point2(0,0);
        public override void Act()
        {
            _parent.MoveIfPossible(_direction.PosX, _direction.PosY);
            target.Reset(_direction.PosX + _parent.GetLocation().PosX, _direction.PosY + _parent.GetLocation().PosY);
            if (CoordVerifier.IsBlocked(target) && _parent.IsCooledDown())
            {
                _direction.SetX(RNG.Rand.Next(3) - 1);
                _direction.SetY(RNG.Rand.Next(3) - 1);                
            }           
        }
    }
}
