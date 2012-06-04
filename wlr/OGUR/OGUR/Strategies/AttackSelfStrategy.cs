using OGUR.Creatures;
using OGUR.Skills;
using System;

namespace OGUR.Strategies
{
    [Serializable]
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