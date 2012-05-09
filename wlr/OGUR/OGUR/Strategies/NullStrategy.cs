using OGUR.Creatures;
using OGUR.Skills;
using SPX.Core;

namespace OGUR.Strategies
{
    public class NullStrategy : IStrategy
    {

        public NullStrategy(ICreature parent, params int[] targetTypes)
            : base(parent)
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