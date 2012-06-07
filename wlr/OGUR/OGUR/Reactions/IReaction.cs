using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Reactions
{
    [Serializable]
    public class Reaction
    {
        public virtual void Affect(ICreature target) { }
    }
}
