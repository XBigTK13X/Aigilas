using System;
using OGUR.Collision;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Skills;

namespace OGUR.Creatures
{
    public class CreatureFactory
    {
        private static int s_playerCount = 0;
        private static Random s_rand;
        public static ICreature Create(int creatureType, Point2 position)
        {
            if (s_rand == null)
                s_rand = new Random();
            AbstractCreature result;
            switch (creatureType)
            {
                case CreatureType.PLAYER:
                    result = new Player(s_playerCount++);
                    break;
                default:
                    result = GenerateCreature(creatureType);
                    break;
            }
            result.Setup(position);
            GameplayObjectManager.AddObject(result);
            return result;
        }

        private static AbstractCreature GenerateCreature(int creatureType)
        {
            switch(creatureType)
            {
                case CreatureType.PEON:
                    return new Peon();
                case CreatureType.ZORB:
                    return new Zorb();
                default:
                    throw new Exception("You forgot to define Factory generation logic for: "+creatureType);
            }
        }

        public static ICreature CreateRandom(Point2 randomPoint)
        {
            var val = s_rand.Next(0, Generate.Randoms.Count);
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
                default:
                    throw new Exception("No minion was defined for the given skillId.");
            }
            result.Init(source, effectGraphic);
            if (location != null)
            {
                result.SetLocation(location);
            }
            GameplayObjectManager.AddObject(result);
            return result;
        }

        public static void ResetPlayerCount()
        {
            s_playerCount = 0;
        }

        public static void IncreasePlayerCount()
        {
            s_playerCount ++;
        }

        public static int GetPlayerCount()
        {
            return s_playerCount;
        }
    }
}