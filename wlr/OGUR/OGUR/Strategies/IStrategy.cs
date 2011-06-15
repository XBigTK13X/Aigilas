using OGUR.Creatures;

namespace OGUR.Strategies
{
    internal abstract class IStrategy
    {
        public abstract void Act(ICreature target);
    }
}