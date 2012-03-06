using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Reactions
{
    public class SweatReaction : IReaction
    {
        public void Affect(Creatures.ICreature target)
        {
            target.AddBuff(new StatBuff(StatType.MOVE_COOL_DOWN, 10));
        }
    }
    public class MagmaReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            target.ApplyDamage(30f);
        }
    }

    public class ExplosionReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class ScorchReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class BlindReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class LacticAcidReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class MindBlownReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class VentReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class DrownReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class ReflectReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class DrenchReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class PneumoniaReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class LobotomyReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class RustReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class PurifyReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class EclipseReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class RespectReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class CraftsmanReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class FlashReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class MetabolismReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class FastForwardReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class BlankReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class YinYangReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class ExposeReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class EnlightenReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class AtrophyReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class NeurosisReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }
    public class ConfuseReaction : IReaction
    {
        public void Affect(ICreature target)
        {
        }
    }

}
