using System.Collections.Generic;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public abstract class IStrategy
    {
        public abstract void Act(ICreature target);
    }
}