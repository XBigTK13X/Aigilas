using System;
using OGUR.Collision;
using OGUR.GameObjects;
using OGUR.Sprites;

namespace OGUR.Creatures
{
    public class CreatureFactory
    {
        private static int s_playerCount = 0;

        public static ICreature Create(CreatureType type, int x, int y)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return (Player) GameplayObjectManager.AddObject(new Player(x, y, s_playerCount++));
                case CreatureType.GOBLIN:
                    return (NormalCreature)GameplayObjectManager.AddObject(new NormalCreature(type, new Point2(x, y), new Stats(50f, 10f, 1f, 1f, 1f, 20f, 1f,10f,1.5f,Stats.DefaultMoveSpeed,Stats.DefaultCoolDown*3)));
                default:
                   throw new Exception("Attempted to create an undefined Creature type in the CreatureFactory.");
            }
        }

        public static ICreature Create(CreatureType type, Point2 position)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return (Player)GameplayObjectManager.AddObject(new Player(position, s_playerCount++));
                case CreatureType.GOBLIN:
                    return (NormalCreature)GameplayObjectManager.AddObject(new NormalCreature(type, position, new Stats(50f, 10f, 1f, 1f, 1f, 20f, 1f, 10f, 1.5f, Stats.DefaultMoveSpeed, Stats.DefaultCoolDown * 3)));
                default:
                    throw new Exception("Attempted to create an undefined Creature type in the CreatureFactory.");
            }
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

        public static ICreature CreateRandom(Point2 randomPoint)
        {
            var rand = new Random();
            return Create((CreatureType) rand.Next(1, Enum.GetValues(typeof (CreatureType)).Length-1),randomPoint);
        }
    }
}