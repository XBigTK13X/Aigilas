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
                case OGUR.EntityType.FLOOR:
                    return new Floor(location);
                case OGUR.EntityType.WALL:
                    return new Wall(location);
                case OGUR.EntityType.DOWNSTAIRS:
                    return new Downstairs(location);
                case OGUR.EntityType.UPSTAIRS:
                    return new Upstairs(location);
                default:
                    throw new Exception("An undefined int case was passed into the EntityFactory.");
            }
        }
    }
}