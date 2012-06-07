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
    public class SweatReaction : Reaction
    {
        public override void Affect(Creatures.ICreature target)
        {
            StatusFactory.Apply(target, Status.SlowDown);
        }
    }
    public class MagmaReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(30f);
        }
    }

    public class ExplosionReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            foreach (var creature in EntityManager.GetActorsSurrounding(target.GetLocation(), 2))
            {
                (creature as ICreature).ApplyDamage(10);
            }
        }
    }
    public class ScorchReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.WeakKnees);
        }
    }
    public class BlindReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Blind);
        }
    }
    public class LacticAcidReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.WeakMuscles);
        }
    }
    public class MindBlownReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(10, null, true, StatType.MANA);
        }
    }
    public class VentReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.SlowDown);
        }
    }
    public class DrownReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Mute);
        }
    }
    public class ReflectReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.HealReflect);
        }
    }
    public class DrenchReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.SoakingWet);
        }
    }
    public class PneumoniaReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Poison);
        }
    }
    public class LobotomyReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.IntDown);
        }
    }
    public class RustReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            target.DestroyRandomItemFromInventory();
        }
    }
    public class PurifyReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventDarkUsage);
        }
    }
    public class EclipseReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventLightUsage);
        }
    }
    public class RespectReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Mutiny);
        }
    }
    public class CraftsmanReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            ItemFactory.CreateRandomPlain(target.GetLocation());
        }
    }
    public class FlashReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            target.SetLocation(EntityManager.GetEmptyLocation());
        }
    }
    public class MetabolismReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventRegeneration);
        }
    }
    public class FastForwardReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(-1, null, false, StatType.AGE);
        }
    }
    public class BlankReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.PreventMentalUsage);
        }
    }
    public class YinYangReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.SelfMutilation);
        }
    }
    public class ExposeReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Flee);
        }
    }
    public class EnlightenReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Mutiny);
        }
    }
    public class AtrophyReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.WeakenStrength);
        }
    }
    public class NeurosisReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.LockSkillCycle);
        }
    }
    public class ConfuseReaction : Reaction
    {
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Confusion);
        }
    }

}
