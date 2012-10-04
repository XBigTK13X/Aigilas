package aigilas.creatures.impl;

import aigilas.creatures.*;
import aigilas.entities.SkillEffect;
import aigilas.items.ItemFactory;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;
import sps.core.Point2;
import sps.core.RNG;
import sps.entities.EntityManager;
import sps.entities.IEntity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CreatureFactory {
    private static int __playerCount = 0;

    public static BaseCreature create(ActorType actorType, Point2 position) {
        BaseEnemy result;
        switch (actorType) {
            case PLAYER:
                result = new Player(__playerCount++);
                break;
            default:
                result = generateCreature(actorType);
                break;
        }
        result.setup(position);
        EntityManager.get().addObject(result);
        // $$$ Testing inv HUD
        for (int ii = 0; ii < 100; ii++) {
            result.pickupItem(ItemFactory.createRandomPlain());
        }
        return result;
    }

    private static BaseEnemy generateCreature(ActorType actorType) {
        switch (actorType) {
            case PEON:
                return new Peon();
            case ZORB:
                return new Zorb();
            case WRATH:
                return new Wrath();
            case HAND:
                return new Hand();
            case PRIDE:
                return new Pride();
            case ENVY:
                return new Envy();
            case GLUTTONY:
                return new Gluttony();
            case LUST:
                return new Lust();
            case SLOTH:
                return new Sloth();
            case GREED:
                return new Greed();
            case SERPENT:
                return new Serpent();
            case BREAKING_WHEEL:
                return new BreakingWheel();
            case WRATH_ACOLYTE:
                return new WrathEnemyAcolyte();
            case PRIDE_ACOLYTE:
                return new PrideEnemyAcolyte();
            case ENVY_ACOLYTE:
                return new EnvyEnemyAcolyte();
            case GLUTTONY_ACOLYTE:
                return new GluttonyEnemyAcolyte();
            case LUST_ACOLYTE:
                return new LustEnemyAcolyte();
            case SLOTH_ACOLYTE:
                return new SlothEnemyAcolyte();
            case GREED_ACOLYTE:
                return new GreedEnemyAcolyte();
            default:
                try {
                    throw new Exception("No Factory generation logic for: " + actorType);
                } catch (Exception e) {

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
            case ACID_NOZZLE:
                result = new AcidNozzle();
                break;
            case DART_TRAP:
                result = new DartTrap();
                break;
            case EXPLODE:
                result = new Explosion();
                break;
            case ICE_SHARD:
                result = new IceShard();
                break;
            case VAPOR_CLOUD:
                result = new VaporCloud();
                break;
            case PLAGUE:
                result = new PoisonCloud();
                break;
            default:
                try {
                    throw new Exception("No minion was defined for the given skillId.");
                } catch (Exception e) {

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
        __remainingBosses = new LinkedList<ActorType>(Arrays.asList(ActorType.WRATH, ActorType.ENVY, ActorType.PRIDE, ActorType.SLOTH, ActorType.GREED, ActorType.LUST, ActorType.GLUTTONY));
    }

    public static void reset(){
        __remainingBosses = new LinkedList<ActorType>(Arrays.asList(ActorType.WRATH, ActorType.ENVY, ActorType.PRIDE, ActorType.SLOTH, ActorType.GREED, ActorType.LUST, ActorType.GLUTTONY));
        __playerCount = 0;
    }

    public static int bossesRemaining() {
        return __remainingBosses.size();
    }

    public static IEntity createNextBoss(Point2 randomPoint) {
        ActorType nextBoss = __remainingBosses.get(RNG.next(0, __remainingBosses.size()));
        __remainingBosses.remove(nextBoss);
        return create(nextBoss, randomPoint);
    }
}
