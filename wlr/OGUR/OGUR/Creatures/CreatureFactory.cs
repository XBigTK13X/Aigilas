using System;
using OGUR.Entities;
using OGUR.Skills;
using SPX.Core;
using SPX.Entities;

namespace OGUR.Creatures
{
    public class CreatureFactory
    {
        private static int __playerCount = 0;
        private static Random __rand;
        public static ICreature Create(int actorType, Point2 position)
        {
            if (__rand == null)
                __rand = new Random();
            AbstractCreature result;
            switch (actorType)
            {
                case OgurActorType.PLAYER:
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
                case OgurActorType.PEON:return new Peon();
                case OgurActorType.ZORB:return new Zorb();
                case OgurActorType.WRATH:return new Wrath();
                case OgurActorType.HAND: return new Hand();
                case OgurActorType.PRIDE: return new Pride();
                case OgurActorType.ENVY: return new Envy();
                case OgurActorType.GLUTTONY: return new Gluttony();
                case OgurActorType.LUST: return new Lust();
                case OgurActorType.SLOTH: return new Sloth();
                case OgurActorType.GREED: return new Greed();
                default:throw new Exception("No Factory generation logic for: "+actorType);
            }
        }

        public static ICreature CreateRandom(Point2 randomPoint)
        {
            var val = __rand.Next(0, Generate.Randoms.Count);
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
    }
}