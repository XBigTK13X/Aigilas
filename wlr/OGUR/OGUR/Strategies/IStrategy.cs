using System.Collections;
using System.Collections.Generic;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public abstract class IStrategy
    {
        protected TargetSet m_targets;
        protected ICreature m_parent;
        
        protected IStrategy(ICreature parent)
        {
            m_targets = new TargetSet(parent);
            m_parent = parent;
        }
        public abstract void Act();

        public TargetSet GetTargets()
        {
            return m_targets;
        }
    }
}