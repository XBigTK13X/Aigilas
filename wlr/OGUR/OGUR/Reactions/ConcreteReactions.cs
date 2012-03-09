using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Statuses;
using OGUR.Items;
using SPX.Entities;

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
            foreach (var creature in EntityManager.GetActorsSurrounding(target.GetLocation(), 2))
            {
                (creature as ICreature).ApplyDamage(10);
            }
        }
    }
    public class ScorchReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.WeakKnees);
        }
    }
    public class BlindReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Blind);
        }
    }
    public class LacticAcidReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.WeakMuscles);
        }
    }
    public class MindBlownReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            target.ApplyDamage(10, null, true, StatType.MANA);
        }
    }
    public class VentReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.SlowDown);
        }
    }
    public class DrownReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Mute);
        }
    }
    public class ReflectReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.HealReflect);
        }
    }
    public class DrenchReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.SoakingWet);
        }
    }
    public class PneumoniaReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Poison);
        }
    }
    public class LobotomyReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.IntDown);
        }
    }
    public class RustReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            target.DestroyRandomItemFromInventory();
        }
    }
    public class PurifyReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventDarkUsage);
        }
    }
    public class EclipseReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventLightUsage);
        }
    }
    public class RespectReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Mutiny);
        }
    }
    public class CraftsmanReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            ItemFactory.CreateRandomPlain(target.GetLocation());
        }
    }
    public class FlashReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            target.SetLocation(EntityManager.GetEmptyLocation());
        }
    }
    public class MetabolismReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventRegeneration);
        }
    }
    public class FastForwardReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            target.ApplyDamage(-1, null, false, StatType.AGE);
        }
    }
    public class BlankReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventMentalUsage);
        }
    }
    public class YinYangReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.SelfMutilation);
        }
    }
    public class ExposeReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Flee);
        }
    }
    public class EnlightenReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Mutiny);
        }
    }
    public class AtrophyReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.WeakenStrength);
        }
    }
    public class NeurosisReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.LockSkillCycle);
        }
    }
    public class ConfuseReaction : IReaction
    {
        public void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Confusion);
        }
    }

}
