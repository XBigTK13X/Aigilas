using System;
using SPX.Entities;
using SPX.Core;

namespace OGUR.Entities
{
    public class EntityFactory
    {
        public static Entity Create(int type, Point2 location)
        {
            switch (type)
            {
                case EntityType.FLOOR:
                    return new Floor(location);
                case EntityType.WALL:
                    return new Wall(location);
                case EntityType.DOWNSTAIRS:
                    return new Downstairs(location);
                case EntityType.UPSTAIRS:
                    return new Upstairs(location);
                default:
                    throw new Exception("An undefined int case was passed into the EntityFactory.");
            }
        }
    }
}