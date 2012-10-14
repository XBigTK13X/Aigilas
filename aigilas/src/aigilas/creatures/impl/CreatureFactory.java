package aigilas.creatures.impl;

import aigilas.creatures.BaseCreature;
import aigilas.entities.SkillEffect;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;
import sps.core.Point2;
import sps.core.RNG;
import sps.entities.Entity;
import sps.entities.EntityManager;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CreatureFactory {
    private static int __playerCount = 0;

    public static BaseCreature create(ActorType actorType, Point2 position) {
        BaseEnemy result;
        switch (actorType) {
            case Player:
                result = new Player(__playerCount++);
                break;
            default:
                result = generateCreature(actorType);
                break;
        }
        result.setup(position);
        EntityManager.get().addObject(result);
        return result;
    }

    private static BaseEnemy generateCreature(ActorType actorType) {
        switch (actorType) {
            case Peon:
                return new Peon();
            case Zorb:
                return new Zorb();
            case Wrath:
                return new Wrath();
            case Hand:
                return new Hand();
            case Pride:
                return new Pride();
            case Envy:
                return new Envy();
            case Gluttony:
                return new Gluttony();
            case Lust:
                return new Lust();
            case Sloth:
                return new Sloth();
            case Greed:
                return new Greed();
            case Serpent:
                return new Serpent();
            case Breaking_Wheel:
                return new BreakingWheel();
            case Wrath_Acolyte:
                return new WrathEnemyAcolyte();
            case Pride_Acolyte:
                return new PrideEnemyAcolyte();
            case Envy_Acolyte:
                return new EnvyEnemyAcolyte();
            case Gluttony_Acolyte:
                return new GluttonyEnemyAcolyte();
            case Lust_Acolyte:
                return new LustEnemyAcolyte();
            case Sloth_Acolyte:
                return new SlothEnemyAcolyte();
            case Greed_Acolyte:
                return new GreedEnemyAcolyte();
            default:
                try {
                    throw new Exception("No Factory generation logic for: " + actorType);
                }
                catch (Exception e) {

                    e.printStackTrace();
                }
                return null;
        }
    }

    public static BaseCreature createRandom(Point2 randomPoint) {
        return create(ActorType.getRandomGeneratable(), randomPoint);
    }

    public static BaseCreature createMinion(SkillId skillId, BaseCreature source, SkillEffect effectGraphic, Point2 location) {
        Minion result = null;
        switch (skillId) {
            case Acid_Nozzle:
                result = new AcidNozzle();
                break;
            case Dart_Trap:
                result = new DartTrap();
                break;
            case Explode:
                result = new Explosion();
                break;
            case Ice_Shard:
                result = new IceShard();
                break;
            case Vapor_Cloud:
                result = new VaporCloud();
                break;
            case Plague:
                result = new PoisonCloud();
                break;
            default:
                try {
                    throw new Exception("No minion was defined for the given skillId.");
                }
                catch (Exception e) {

                    e.printStackTrace();
                }
        }
        result.init(source, effectGraphic);
        if (location != null) {
            result.setLocation(location);
        }
        EntityManager.get().addObject(result);
        return result;
    }

    public static BaseCreature createMinion(SkillId skillId, BaseCreature source, SkillEffect effectGraphic) {
        return createMinion(skillId, source, effectGraphic, null);
    }

    public static BaseCreature createMinion(SkillId skillId, BaseCreature source) {
        return createMinion(skillId, source, null, null);
    }

    private static List<ActorType> __remainingBosses;

    static {
        __remainingBosses = new LinkedList<ActorType>(Arrays.asList(ActorType.Wrath, ActorType.Envy, ActorType.Pride, ActorType.Sloth, ActorType.Greed, ActorType.Lust, ActorType.Gluttony));
    }

    public static void reset() {
        __remainingBosses = new LinkedList<ActorType>(Arrays.asList(ActorType.Wrath, ActorType.Envy, ActorType.Pride, ActorType.Sloth, ActorType.Greed, ActorType.Lust, ActorType.Gluttony));
        __playerCount = 0;
    }

    public static int bossesRemaining() {
        return __remainingBosses.size();
    }

    public static Entity createNextBoss(Point2 randomPoint) {
        ActorType nextBoss = __remainingBosses.get(RNG.next(0, __remainingBosses.size()));
        __remainingBosses.remove(nextBoss);
        return create(nextBoss, randomPoint);
    }
}
