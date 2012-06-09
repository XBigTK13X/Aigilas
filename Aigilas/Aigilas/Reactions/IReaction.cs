using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Agilas.Creatures;

namespace Agilas.Reactions
{
    public interface IReaction
    {
        void Affect(ICreature target);
    }
}
