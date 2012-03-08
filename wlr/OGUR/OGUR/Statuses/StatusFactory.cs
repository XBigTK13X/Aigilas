using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Statuses
{
    class StatusFactory
    {
        public static void Apply(ICreature target, int statusId)
        {
            switch (statusId)
            {
                case Status.Confusion: target.AddStatus(new ConfusionStatus(target)); break;
                case Status.Mutiny: target.AddStatus(new MutinyStatus(target)); break;
                case Status.Poison: target.AddStatus(new PoisonStatus(target)); break;
                case Status.Regen: target.AddStatus(new RegenStatus(target)); break;
                case Status.StrengthUp: target.AddStatus(new StrengthUpStatus(target)); break;                
                case Status.VenomFist: target.AddStatus(new VenomFistStatus(target)); break;
                case Status.WeakKnees: target.AddStatus(new WeakKneesStatus(target)); break;
                case Status.SpeedUp: target.AddStatus(new SpeedUpStatus(target)); break;
                case Status.ManaUp: target.AddStatus(new ManaUpStatus(target)); break;
                case Status.Electrify: target.AddStatus(new ElectrifyStatus(target)); break;
                case Status.Zap: target.AddStatus(new ZapStatus(target)); break;
                case Status.PoisonOneHit: target.AddStatus(new PoisonOneHitStatus(target));break;
                case Status.DefenseUp: target.AddStatus(new DefenseUpStatus(target)); break;
                case Status.ColdShoulder: target.AddStatus(new BurnOneHitStatus(target)); break;
                case Status.Burn: target.AddStatus(new BurnStatus(target)); break;
                case Status.Flee: target.AddStatus(new FleeStatus(target)); break;
                case Status.Berserk: target.AddStatus(new BerserkStatus(target)); break;
                case Status.RandomBuff: target.AddStatus(new RandomBuffStatus(target)); break;
                case Status.Hord: target.AddStatus(new HordStatus(target)); break;
                case Status.LockSkillCycle: target.AddStatus(new LockSkillCycleStatus(target)); break;
                case Status.WeakenStrength: target.AddStatus(new WeakenStrengthStatus(target)); break;
                case Status.SelfMutilation: target.AddStatus(new SelfMutilationStatus(target)); break;
                case Status.PreventMentalUsage: target.AddStatus(new PreventMentalUsageStatus(target)); break;
                case Status.PreventRegeneration: target.AddStatus(new PreventRegenerationStatus(target)); break;
                case Status.PreventLightUsage: target.AddStatus(new PreventLightUsageStatus(target)); break;
                case Status.PreventDarkUsage: target.AddStatus(new PreventDarkUsageStatus(target)); break;
                case Status.FragileItems: target.AddStatus(new FragileItemsStatus(target)); break;
                case Status.IntDown: target.AddStatus(new IntDownStatus(target)); break;
                case Status.SoakingWet: target.AddStatus(new SoakingWetStatus(target)); break;
                case Status.HealReflect: target.AddStatus(new HealReflectStatus(target)); break;
                case Status.Mute: target.AddStatus(new MuteStatus(target)); break;
                case Status.SlowDown: target.AddStatus(new SlowDownStatus(target)); break;
                case Status.WeakMuscles: target.AddStatus(new WeakMusclesStatus(target)); break;
                case Status.Blind: target.AddStatus(new BlindStatus(target)); break;
                default:
                    throw new Exception(String.Format("An undefined statusId {0} was passed StatusFactory.Apply.", statusId));
            }
        }
    }
}
