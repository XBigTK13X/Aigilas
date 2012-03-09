using OGUR.Creatures;
using OGUR.Skills;

namespace OGUR.Strategies
{
    public class AttackSelfStrategy : IStrategy
    {
        public AttackSelfStrategy(ICreature parent)
            : base(parent)
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