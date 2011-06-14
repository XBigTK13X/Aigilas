using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    abstract class IStrategy
    {
        abstract public void Act(ICreature target);
    }
}
