package aigilas.skills;

import aigilas.management.SpriteType;
import aigilas.skills.animations.*;
import aigilas.skills.behaviors.*;
import aigilas.skills.impl.*;

public class SkillFactory {
    public static BaseSkill create(SkillId idSkill) {
        switch (idSkill) {
            case ABSORB:
                return new AbsorbSkill();
            case ACID_DRIP:
                return new AcidDripSkill();
            case ACID_NOZZLE:
                return new AcidNozzleSkill();
            case BRIMSTONE:
                return new BrimstoneSkill();
            case BOIL:
                return new BoilSkill();
            case BREAKING_WHEEL:
                return new BreakingWheelSkill();
            case CAVALRY:
                return new CavalrySkill();
            case COLD_SHOULDER:
                return new ColdShoulderSkill();
            case COMBUST:
                return new CombustSkill();
            case CONFUSION:
                return new ConfusionSkill();
            case DART:
                return new DartSkill();
            case DART_TRAP:
                return new DartTrapSkill();
            case DISMEMBERMENT:
                return new DismembermentSkill();
            case ELECTRIFY:
                return new ElectrifySkill();
            case ENVENOM:
                return new EnvenomSkill();
            case EXPLODE:
                return new ExplodeSkill();
            case FIREBALL:
                return new FireballSkill();
            case FLAME_HAMMER:
                return new FlameHammerSkill();
            case FLOOR_SPIKES:
                return new FloorSpikesSkill();
            case FORGET_SKILL:
                return new ForgetSkill();
            case GUSH:
                return new GushSkill();
            case HORDER:
                return new HorderSkill();
            case HORRIFY:
                return new HorrifySkill();
            case HYPOTHERMIA:
                return new HypothermiaSkill();
            case ICE_SHARD:
                return new IceShardSkill();
            case MAGIC_MAP:
                return new MagicMapSkill();
            case MANA_UP:
                return new ManaUpSkill();
            case MIMIC:
                return new MimicSkill();
            case MUTINY:
                return new MutinySkill();
            case NO_SKILL:
                return new NoSkill();
            case PLAGUE:
                return new PlagueSkill();
            case POISON_CLOUD:
                return new PoisonCloudSkill();
            case REGEN_ALL:
                return new RegenAllSkill();
            case REMOTE_MINE:
                return new RemoteMineSkill();
            case SERPENT_SUPPER:
                return new SerpentSupperSkill();
            case SOUL_CRUSH:
                return new SoulCrushSkill();
            case SOUL_REINFORCEMENT:
                return new SoulReinforcementSkill();
            case SPAWN_ITEM:
                return new SpawnItemSkill();
            case SPEED_UP:
                return new SpeedUpSkill();
            case STEAL_ITEM:
                return new StealItemSkill();
            case STRENGTH_UP:
                return new StrengthUpSkill();
            case THROW_ITEM:
                return new ThrowItemSkill();
            case VALEDICTORIAN:
                return new ValedictorianSkill();
            case VAPOR_IMPLANT:
                return new VaporImplantSkill();
            case VENOM_FIST:
                return new VenomFistSkill();
            case WALL_PUNCH:
                return new WallPunchSkill();
            case WEAK_KNEES:
                return new WeakKneesSkill();
            case VAPOR_CLOUD:
                return new VaporCloudSkill();
            default:
                try {
                    throw new Exception("A SkillId to Skill mapping was not defined:the SkillFactory for (" + idSkill + "). This is 100% the fault of whoever wrote the code.");
                }
                catch (Exception e) {

                    e.printStackTrace();
                }
                return null;
        }
    }

    public static SkillBehavior create(AnimationType animation, SpriteType skillGraphic, BaseSkill parentSkill) {
        switch (animation) {
            case CLOUD:
                return new CloudBehavior(skillGraphic, parentSkill);
            case RANGED:
                return new RangedBehavior(skillGraphic, parentSkill);
            case SELF:
                return new SelfBehavior(skillGraphic, parentSkill);
            case STATIONARY:
                return new StationaryBehavior(skillGraphic, parentSkill);
            case ROTATE:
                return new RotateBehavior(skillGraphic, parentSkill);
            default:
                try {
                    throw new Exception("How dare you create a new Anim type for skills without defining a proper behavior for them!");
                }
                catch (Exception e) {

                    e.printStackTrace();
                }
                return null;
        }
    }

    public static SkillAnimation create(AnimationType animation) {
        switch (animation) {
            case RANGED:
                return new RangedAnimation();
            case SELF:
                return new SelfAnimation();
            case ROTATE:
                return new RotateAnimation();
            default:
                return new NoAnimation();
        }
    }

    public static float getCost(SkillId skillId) {
        return create(skillId).getCost();
    }
}
