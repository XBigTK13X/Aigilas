using OGUR.Creatures;
using OGUR.Skills;
using SPX.Core;
using System;

namespace OGUR.Strategies
{
    [Serializable]
    public class NullStrategy : IStrategy
    {

        public NullStrategy(ICreature parent, params int[] targetTypes)
            : base(parent,Strategy.Null)
        {
            foreach (var targetType in targetTypes)
            {
                _targets.AddTargetTypes(targetType);
            }
        }

        public override void Act()
        {
            
        }
    }
}