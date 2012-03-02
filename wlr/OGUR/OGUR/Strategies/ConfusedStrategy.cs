using System;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public class ConfusedStrategy : IStrategy
    {
        public ConfusedStrategy(ICreature parent)
            : base(parent)
        {
        }
        public override void Act()
        {
            m_parent.MoveIfPossible(rand.Next(3) - 1,rand.Next(3) - 1);
        }
    }
}