using System;
using OGUR.Collision;

namespace OGUR.GameObjects
{
    public class GameplayObjectFactory
    {
        public static GameplayObject Create(GameObjectType type, int x, int y)
        {
            switch (type)
            {
                case GameObjectType.FLOOR:
                    return new Floor(x, y);
                case GameObjectType.WALL:
                    return new Wall(x, y);
                default:
                    throw new Exception("An undefined GameObjectType case was passed into the GameplayObjectFactory.");
            }
        }
        public static GameplayObject Create(GameObjectType type, Point2 location)
        {
            switch (type)
            {
                case GameObjectType.DOWNSTAIRS:
                    return new Downstairs(location);
                case GameObjectType.UPSTAIRS:
                    return new Upstairs(location);
                default:
                    throw new Exception("An undefined GameObjectType case was passed into the GameplayObjectFactory.");
            }
        }
    }
}