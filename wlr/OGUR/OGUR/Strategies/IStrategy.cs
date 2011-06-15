using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    internal abstract class IStrategy
    {
        public abstract void Act(ICreature target);
    }
}