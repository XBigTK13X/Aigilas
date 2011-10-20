using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Reactions
{
    public enum ReactionId
    {
        SWEAT,
        MAGMA,
        EXPLOSION,
        SCORCH,
        BLIND
    }
    public class Sweat : IReaction
    {
        public override void Affect(Creatures.ICreature target)
        {
            target.AddBuff(new StatBuff(StatType.MOVE_COOL_DOWN, 10));
        }
    }
    public class Magma : IReaction
    {
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(30f);
        }
    }
}
