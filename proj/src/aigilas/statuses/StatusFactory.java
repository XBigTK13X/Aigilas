package aigilas.statuses;

import aigilas.creatures.ICreature;
import aigilas.statuses.impl.*;

public class StatusFactory {
    public static void Apply(ICreature target, Status statusId) {
        switch (statusId) {
            case Confusion:
                target.AddStatus(new ConfusionStatus(target));
                break;
            case Mutiny:
                target.AddStatus(new MutinyStatus(target));
                break;
            case Poison:
                target.AddStatus(new PoisonStatus(target));
                break;
            case Regen:
                target.AddStatus(new RegenStatus(target));
                break;
            case StrengthUp:
                target.AddStatus(new StrengthUpStatus(target));
                break;
            case VenomFist:
                target.AddStatus(new VenomFistStatus(target));
                break;
            case WeakKnees:
                target.AddStatus(new WeakKneesStatus(target));
                break;
            case SpeedUp:
                target.AddStatus(new SpeedUpStatus(target));
                break;
            case ManaUp:
                target.AddStatus(new ManaUpStatus(target));
                break;
            case Electrify:
                target.AddStatus(new ElectrifyStatus(target));
                break;
            case Zap:
                target.AddStatus(new ZapStatus(target));
                break;
            case PoisonOneHit:
                target.AddStatus(new PoisonOneHitStatus(target));
                break;
            case DefenseUp:
                target.AddStatus(new DefenseUpStatus(target));
                break;
            case ColdShoulder:
                target.AddStatus(new BurnOneHitStatus(target));
                break;
            case Burn:
                target.AddStatus(new BurnStatus(target));
                break;
            case Flee:
                target.AddStatus(new FleeStatus(target));
                break;
            case Berserk:
                target.AddStatus(new BerserkStatus(target));
                break;
            case RandomBuff:
                target.AddStatus(new RandomBuffStatus(target));
                break;
            case Hord:
                target.AddStatus(new HordStatus(target));
                break;
            case LockSkillCycle:
                target.AddStatus(new LockSkillCycleStatus(target));
                break;
            case WeakenStrength:
                target.AddStatus(new WeakenStrengthStatus(target));
                break;
            case SelfMutilation:
                target.AddStatus(new SelfMutilationStatus(target));
                break;
            case PreventMentalUsage:
                target.AddStatus(new PreventMentalUsageStatus(target));
                break;
            case PreventRegeneration:
                target.AddStatus(new PreventRegenerationStatus(target));
                break;
            case PreventLightUsage:
                target.AddStatus(new PreventLightUsageStatus(target));
                break;
            case PreventDarkUsage:
                target.AddStatus(new PreventDarkUsageStatus(target));
                break;
            case IntDown:
                target.AddStatus(new IntDownStatus(target));
                break;
            case SoakingWet:
                target.AddStatus(new SoakingWetStatus(target));
                break;
            case HealReflect:
                target.AddStatus(new BlockHealingStatus(target));
                break;
            case Mute:
                target.AddStatus(new MuteStatus(target));
                break;
            case SlowDown:
                target.AddStatus(new SlowDownStatus(target));
                break;
            case WeakMuscles:
                target.AddStatus(new WeakMusclesStatus(target));
                break;
            case Blind:
                target.AddStatus(new BlindStatus(target));
                break;
            case Toxic:
                target.AddStatus(new ToxicStatus(target));
                break;
            case Boil:
                target.AddStatus(new BoilStatus(target));
                break;
            default:
                try {
                    throw new Exception(String.format("An undefined statusId {0} was passed StatusFactory.Apply.", statusId));
                } catch (Exception e) {

                    e.printStackTrace();
                }
        }
    }
}
