package aigilas.statuses;

import aigilas.creatures.BaseCreature;
import aigilas.statuses.impl.*;
import sps.core.Logger;

public class StatusFactory {
    public static void apply(BaseCreature target, Status statusId) {
        switch (statusId) {
            case Confusion:
                target.addStatus(new ConfusionStatus(target));
                break;
            case Mutiny:
                target.addStatus(new MutinyStatus(target));
                break;
            case Poison:
                target.addStatus(new PoisonStatus(target));
                break;
            case Regen:
                target.addStatus(new RegenStatus(target));
                break;
            case StrengthUp:
                target.addStatus(new StrengthUpStatus(target));
                break;
            case VenomFist:
                target.addStatus(new VenomFistStatus(target));
                break;
            case WeakKnees:
                target.addStatus(new WeakKneesStatus(target));
                break;
            case SpeedUp:
                target.addStatus(new SpeedUpStatus(target));
                break;
            case EnergyUp:
                target.addStatus(new EnergyUpStatus(target));
                break;
            case Electrify:
                target.addStatus(new ElectrifyStatus(target));
                break;
            case Zap:
                target.addStatus(new ZapStatus(target));
                break;
            case PoisonOneHit:
                target.addStatus(new PoisonOneHitStatus(target));
                break;
            case DefenseUp:
                target.addStatus(new DefenseUpStatus(target));
                break;
            case ColdShoulder:
                target.addStatus(new BurnOneHitStatus(target));
                break;
            case Burn:
                target.addStatus(new BurnStatus(target));
                break;
            case Flee:
                target.addStatus(new FleeStatus(target));
                break;
            case Berserk:
                target.addStatus(new BerserkStatus(target));
                break;
            case RandomBuff:
                target.addStatus(new RandomBuffStatus(target));
                break;
            case Hord:
                target.addStatus(new HordStatus(target));
                break;
            case LockSkillCycle:
                target.addStatus(new LockSkillCycleStatus(target));
                break;
            case WeakenStrength:
                target.addStatus(new WeakenStrengthStatus(target));
                break;
            case SelfMutilation:
                target.addStatus(new SelfMutilationStatus(target));
                break;
            case PreventMentalUsage:
                target.addStatus(new PreventMentalUsageStatus(target));
                break;
            case PreventRegeneration:
                target.addStatus(new PreventRegenerationStatus(target));
                break;
            case PreventLightUsage:
                target.addStatus(new PreventLightUsageStatus(target));
                break;
            case PreventDarkUsage:
                target.addStatus(new PreventDarkUsageStatus(target));
                break;
            case IntDown:
                target.addStatus(new IntDownStatus(target));
                break;
            case SoakingWet:
                target.addStatus(new SoakingWetStatus(target));
                break;
            case HealReflect:
                target.addStatus(new BlockHealingStatus(target));
                break;
            case Mute:
                target.addStatus(new MuteStatus(target));
                break;
            case SlowDown:
                target.addStatus(new SlowDownStatus(target));
                break;
            case WeakMuscles:
                target.addStatus(new WeakMusclesStatus(target));
                break;
            case Blind:
                target.addStatus(new BlindStatus(target));
                break;
            case Toxic:
                target.addStatus(new ToxicStatus(target));
                break;
            case Boil:
                target.addStatus(new BoilStatus(target));
                break;
            default:
                Logger.error(String.format("An undefined statusId %s was passed StatusFactory.Apply.", statusId));
        }
    }
}
