using System;
using Agilas.Entities;
using Agilas.Skills;
using SPX.Core;
using SPX.Entities;
using System.Collections.Generic;

namespace Agilas.Creatures
{
    public class CreatureFactory
    {
        private static int __playerCount = 0;
        public static ICreature Create(int actorType, Point2 position)
        {
            AbstractCreature result;
            switch (actorType)
            {
                case AgilasActorType.PLAYER:
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
                case AgilasActorType.PEON:return new Peon();
                case AgilasActorType.ZORB:return new Zorb();
                case AgilasActorType.WRATH:return new Wrath();
                case AgilasActorType.HAND: return new Hand();
                case AgilasActorType.PRIDE: return new Pride();
                case AgilasActorType.ENVY: return new Envy();
                case AgilasActorType.GLUTTONY: return new Gluttony();
                case AgilasActorType.LUST: return new Lust();
                case AgilasActorType.SLOTH: return new Sloth();
                case AgilasActorType.GREED: return new Greed();
                case AgilasActorType.SERPENT: return new Serpent();
                case AgilasActorType.BREAKING_WHEEL: return new BreakingWheel();
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

        public static void ResetPlayerCount()
        {
            __playerCount = 0;
        }

        public static void IncreasePlayerCount()
        {
            __playerCount ++;
        }

        public static int GetPlayerCount()
        {
            return __playerCount;
        }

        private static List<int> __remainingBosses = new List<int>()
        {
            AgilasActorType.WRATH,
            AgilasActorType.ENVY,
            AgilasActorType.PRIDE,
            AgilasActorType.SLOTH,
            AgilasActorType.GREED,
            AgilasActorType.LUST,
            AgilasActorType.GLUTTONY
        };
        public static IEntity CreateNextBoss(Point2 randomPoint)
        {
            int nextBoss = __remainingBosses[RNG.Rand.Next(0,__remainingBosses.Count)];
            __remainingBosses.Remove(nextBoss);
            return Create(nextBoss,randomPoint);
        }
    }
}