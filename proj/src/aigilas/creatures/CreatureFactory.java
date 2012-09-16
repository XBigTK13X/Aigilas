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

    public static ICreature Create(ActorType actorType, Point2 position) {
        AbstractCreature result;
        switch (actorType) {
            case PLAYER:
                result = new Player(__playerCount++);
                break;
            default:
                result = GenerateCreature(actorType);
                break;
        }
        result.Setup(position);
        EntityManager.addObject(result);
        // $$$ Testing inv HUD
        for (int ii = 0; ii < 100; ii++) {
            result.PickupItem(ItemFactory.CreateRandomPlain());
        }
        return result;
    }

    private static AbstractCreature GenerateCreature(ActorType actorType) {
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

    public static ICreature CreateRandom(Point2 randomPoint) {
        return Create(Generate.Randoms.get(RNG.Next(0, Generate.Randoms.size())), randomPoint);
    }

    public static ICreature CreateMinion(SkillId skillId, ICreature source, SkillEffect effectGraphic, Point2 location) {
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
        result.Init(source, effectGraphic);
        if (location != null) {
            result.SetLocation(location);
        }
        EntityManager.addObject(result);
        return result;
    }

    public static ICreature CreateMinion(SkillId skillId, ICreature source, SkillEffect effectGraphic) {
        return CreateMinion(skillId, source, effectGraphic, null);
    }

    public static ICreature CreateMinion(SkillId skillId, ICreature source) {
        return CreateMinion(skillId, source, null, null);
    }

    private static List<ActorType> __remainingBosses;

    static {
        __remainingBosses = new LinkedList<ActorType>(Arrays.asList(ActorType.WRATH, ActorType.ENVY, ActorType.PRIDE, ActorType.SLOTH, ActorType.GREED, ActorType.LUST, ActorType.GLUTTONY));
    }

    ;

    public static int BossesRemaining() {
        return __remainingBosses.size();
    }

    public static IEntity CreateNextBoss(Point2 randomPoint) {
        ActorType nextBoss = __remainingBosses.get(RNG.Next(0, __remainingBosses.size()));
        __remainingBosses.remove(nextBoss);
        return Create(nextBoss, randomPoint);
    }
}
