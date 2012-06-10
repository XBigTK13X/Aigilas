using Aigilas.Creatures;
using Aigilas.Skills;

namespace Aigilas.Strategies
{
    public class AttackSelfStrategy : IStrategy
    {
        public AttackSelfStrategy(ICreature parent)
            : base(parent,Strategy.AttackSelf)
        {
        }

        public override void Act()
        {
            if (AbleToMove())
            {
                _parent.ApplyDamage(1);
            }
        }
    }
}