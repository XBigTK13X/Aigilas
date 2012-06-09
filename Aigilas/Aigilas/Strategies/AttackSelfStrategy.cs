using Agilas.Creatures;
using Agilas.Skills;

namespace Agilas.Strategies
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