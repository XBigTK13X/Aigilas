using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Aigilas.Creatures;

namespace Aigilas.Reactions
{
    public interface IReaction
    {
        void Affect(ICreature target);
    }
}
