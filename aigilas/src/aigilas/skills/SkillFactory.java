package aigilas.skills;

import aigilas.management.SpriteType;
import aigilas.skills.animations.*;
import aigilas.skills.behaviors.*;
import aigilas.skills.impl.*;

public class SkillFactory {
    public static BaseSkill create(SkillId idSkill) {
        switch (idSkill) {
            case Absorb:
                return new AbsorbSkill();
            case Acid_Drip:
                return new AcidDripSkill();
            case Acid_Nozzle:
                return new AcidNozzleSkill();
            case Brimstone:
                return new BrimstoneSkill();
            case Boil:
                return new BoilSkill();
            case Breaking_Wheel:
                return new BreakingWheelSkill();
            case Cavalry:
                return new CavalrySkill();
            case Cold_Shoulder:
                return new ColdShoulderSkill();
            case Combust:
                return new CombustSkill();
            case Confusion:
                return new ConfusionSkill();
            case Dart:
                return new DartSkill();
            case Dart_Trap:
                return new DartTrapSkill();
            case Dismemberment:
                return new DismembermentSkill();
            case Electrify:
                return new ElectrifySkill();
            case Envenom:
                return new EnvenomSkill();
            case Explode:
                return new ExplodeSkill();
            case Fireball:
                return new FireballSkill();
            case Flame_Hammer:
                return new FlameHammerSkill();
            case Floor_Spikes:
                return new FloorSpikesSkill();
            case Forget_Skill:
                return new ForgetSkill();
            case Gush:
                return new GushSkill();
            case Horder:
                return new HorderSkill();
            case Horrify:
                return new HorrifySkill();
            case Hypothermia:
                return new HypothermiaSkill();
            case Ice_Shard:
                return new IceShardSkill();
            case Magic_Map:
                return new MagicMapSkill();
            case Mana_Up:
                return new ManaUpSkill();
            case Mimic:
                return new MimicSkill();
            case Mutiny:
                return new MutinySkill();
            case No_Skill:
                return new NoSkill();
            case Plague:
                return new PlagueSkill();
            case Poison_Cloud:
                return new PoisonCloudSkill();
            case Regen_All:
                return new RegenAllSkill();
            case Remote_Mine:
                return new RemoteMineSkill();
            case Serpent_Supper:
                return new SerpentSupperSkill();
            case Soul_Crush:
                return new SoulCrushSkill();
            case Soul_Reinforcement:
                return new SoulReinforcementSkill();
            case Spawn_Item:
                return new SpawnItemSkill();
            case Speed_Up:
                return new SpeedUpSkill();
            case Steal_Item:
                return new StealItemSkill();
            case Strength_Up:
                return new StrengthUpSkill();
            case Throw_Item:
                return new ThrowItemSkill();
            case Valedictorian:
                return new ValedictorianSkill();
            case Vapor_Implant:
                return new VaporImplantSkill();
            case Venom_Fist:
                return new VenomFistSkill();
            case Wall_Punch:
                return new WallPunchSkill();
            case Weak_Knees:
                return new WeakKneesSkill();
            case Vapor_Cloud:
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
        return create(skillId).behavior().getCost();
    }
}
