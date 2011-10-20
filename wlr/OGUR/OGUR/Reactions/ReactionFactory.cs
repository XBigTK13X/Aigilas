using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Reactions
{
    public class ReactionFactory
    {
        public static IReaction Create(ReactionId id)
        {
            switch(id)
            {
                case ReactionId.SWEAT:
                    return new Sweat();
                case ReactionId.MAGMA:
                    return new Magma();
                default:
                    return null;
            }
        }
    }
}
