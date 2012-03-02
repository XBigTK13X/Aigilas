using System;
using OGUR.Collision;

namespace OGUR.GameObjects
{
    public class EntityFactory
    {
        public static Entity Create(int type, Point2 location)
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
                    throw new Exception("An undefined int case was passed into the EntityFactory.");
            }
        }
    }
}