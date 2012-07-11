using System;
using Aigilas.Entities;
using Aigilas.Skills;
using SPX.Core;
using SPX.Entities;
using System.Collections.Generic;

namespace Aigilas.Creatures
{
    public class CreatureFactory
    {
        private static int __playerCount = 0;

        public static ICreature Create(int actorType, Point2 position)
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
            EntityManager.AddObject(result);
            return result;
        }

        private static AbstractCreature GenerateCreature(int actorType)
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

        public static ICreature CreateRandom(Point2 randomPoint)
        {
            var val = RNG.Rand.Next(0, Generate.Randoms.Count);
            return Create(Generate.Randoms[val], randomPoint);
        }

        public static ICreature CreateMinion(string skillId, ICreature source,SkillEffect effectGraphic=null,Point2 location=null)
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
            EntityManager.AddObject(result);
            return result;
        }

        private static List<int> __remainingBosses = new List<int>()
        {
            AigilasActorType.WRATH,
            AigilasActorType.ENVY,
            AigilasActorType.PRIDE,
            AigilasActorType.SLOTH,
            AigilasActorType.GREED,
            AigilasActorType.LUST,
            AigilasActorType.GLUTTONY
        };

        public static int BossesRemaining()
        {
            return __remainingBosses.Count;
        }

        public static IEntity CreateNextBoss(Point2 randomPoint)
        {
            int nextBoss = __remainingBosses[RNG.Rand.Next(0,__remainingBosses.Count)];
            __remainingBosses.Remove(nextBoss);
            return Create(nextBoss,randomPoint);
        }
    }
}