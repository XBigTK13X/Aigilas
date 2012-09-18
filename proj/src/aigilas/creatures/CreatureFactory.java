package aigilas.creatures;

import aigilas.entities.SkillEffect;
import aigilas.items.ItemFactory;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.RNG;
import spx.entities.EntityManager;
import spx.entities.IEntity;

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
        EntityManager.addObject(result);
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
        return create(Generate.Randoms.get(RNG.next(0, Generate.Randoms.size())), randomPoint);
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
        EntityManager.addObject(result);
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
        __remainingBosses = new LinkedList<>(Arrays.asList(ActorType.WRATH, ActorType.ENVY, ActorType.PRIDE, ActorType.SLOTH, ActorType.GREED, ActorType.LUST, ActorType.GLUTTONY));
    }

    public static void reset(){
        __remainingBosses = new LinkedList<>(Arrays.asList(ActorType.WRATH, ActorType.ENVY, ActorType.PRIDE, ActorType.SLOTH, ActorType.GREED, ActorType.LUST, ActorType.GLUTTONY));
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
