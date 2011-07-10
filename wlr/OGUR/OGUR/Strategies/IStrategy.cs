using System.Collections;
using System.Collections.Generic;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public abstract class IStrategy
    {
        protected TargetSet m_targets;
        protected IStrategy(ICreature parent)
        {
            m_targets = new TargetSet(parent);
        }
        public abstract void Act(ICreature parent);

        public TargetSet GetTargets()
        {
            return m_targets;
        }
    }
}