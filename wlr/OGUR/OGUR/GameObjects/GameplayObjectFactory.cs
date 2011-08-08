using System;
using OGUR.Collision;

namespace OGUR.GameObjects
{
    public class GameplayObjectFactory
    {
        public static GameplayObject Create(GameObjectType type, Point2 location)
        {
            switch (type)
            {
                case GameObjectType.FLOOR:
                    return new Floor(location);
                case GameObjectType.WALL:
                    return new Wall(location);
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