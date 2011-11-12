using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Reactions
{
    public class ReactionId
    {
        public const int SWEAT = 0;
        public const int MAGMA = 1;
        public const int EXPLOSION = 2;
        public const int SCORCH = 3;
        public const int BLIND = 4;
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
