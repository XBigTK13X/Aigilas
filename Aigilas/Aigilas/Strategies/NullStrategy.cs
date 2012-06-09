using Agilas.Creatures;
using Agilas.Skills;
using SPX.Core;

namespace Agilas.Strategies
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