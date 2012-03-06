using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Reactions
{
    public interface IReaction
    {
        void Affect(ICreature target);
    }
}
