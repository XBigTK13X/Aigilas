using Aigilas.Creatures;
using Aigilas.Skills;
using SPX.Core;

namespace Aigilas.Strategies
{
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