package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.entities.*;import com.aigilas.skills.*;import com.spx.core.*;import com.spx.entities.*;import com.spx.devtools.*;
    public class CreatureFactory
    {
        private static int __playerCount = 0;

        public static ICreature Create(int actorType, Point2 position) throws Exception
        {
            AbstractCreature result;
            switch (actorType)
            {
                case AigilasActorType.PLAYER:
                    result = new Player(__playerCount++);
                    break;
                default:
                    result = GenerateCreature(actorType);
                    break;
            }
            result.Setup(position);
            EntityManager.addObject(result);
            return result;
        }

        private static AbstractCreature GenerateCreature(int actorType) throws Exception
        {
            switch(actorType)
            {
                case AigilasActorType.PEON:return new Peon();
                case AigilasActorType.ZORB:return new Zorb();
                case AigilasActorType.WRATH:return new Wrath();
                case AigilasActorType.HAND: return new Hand();
                case AigilasActorType.PRIDE: return new Pride();
                case AigilasActorType.ENVY: return new Envy();
                case AigilasActorType.GLUTTONY: return new Gluttony();
                case AigilasActorType.LUST: return new Lust();
                case AigilasActorType.SLOTH: return new Sloth();
                case AigilasActorType.GREED: return new Greed();
                case AigilasActorType.SERPENT: return new Serpent();
                case AigilasActorType.BREAKING_WHEEL: return new BreakingWheel();
                default:throw new Exception("No Factory generation logic for: "+actorType);
            }
        }

        public static ICreature CreateRandom(Point2 randomPoint) throws Exception
        {            
            return Create(Generate.Randoms.get(RNG.Next(0, Generate.Randoms.size())), randomPoint);
        }

        public static ICreature CreateMinion(String skillId, ICreature source,SkillEffect effectGraphic,Point2 location) throws Exception
        {
            Minion result = null;
            switch (skillId)
            {
                case SkillId.ACID_NOZZLE:result = new AcidNozzle();break;
                case SkillId.DART_TRAP: result = new DartTrap(); break;
                case SkillId.EXPLODE: result = new Explosion(); break;
                case SkillId.ICE_SHARD: result = new IceShard(); break; 
                case SkillId.VAPOR_CLOUD: result = new VaporCloud(); break;
                case SkillId.PLAGUE: result = new PoisonCloud(); break;
                default:
                    throw new Exception("No minion was defined for the given skillId.");
            }
            result.Init(source, effectGraphic);
            if (location != null)
            {
                result.SetLocation(location);
            }
            EntityManager.addObject(result);
            return result;
        }        public static ICreature CreateMinion(String skillId, ICreature source, SkillEffect effectGraphic) throws Exception        {            return CreateMinion(skillId,source,effectGraphic,null);        }        public static ICreature CreateMinion(String skillId, ICreature source) throws Exception        {            return CreateMinion(skillId,source,null,null);        }

        private static List<Integer> __remainingBosses;        static
        {        	__remainingBosses = Arrays.asList(
            AigilasActorType.WRATH,
            AigilasActorType.ENVY,
            AigilasActorType.PRIDE,
            AigilasActorType.SLOTH,
            AigilasActorType.GREED,
            AigilasActorType.LUST,
            AigilasActorType.GLUTTONY);
        };

        public static int BossesRemaining()
        {
            return __remainingBosses.size();
        }

        public static IEntity CreateNextBoss(Point2 randomPoint) throws Exception
        {
            int nextBoss = __remainingBosses.get(RNG.Next(0,__remainingBosses.size()));
            __remainingBosses.remove(nextBoss);
            return Create(nextBoss,randomPoint);
        }
    }
