using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Reactions
{
    public abstract class IReaction
    {
        public IReaction()
        {

        }
        public abstract void Affect(ICreature target);
    }
}
