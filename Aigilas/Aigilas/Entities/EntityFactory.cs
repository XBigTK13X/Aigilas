using System;
using SPX.Entities;
using SPX.Core;

namespace Agilas.Entities
{
    public class EntityFactory
    {
        public static Entity Create(int type, Point2 location)
        {
            switch (type)
            {
                case Agilas.EntityType.FLOOR:
                    return new Floor(location);
                case Agilas.EntityType.WALL:
                    return new Wall(location);
                case Agilas.EntityType.DOWNSTAIRS:
                    return new Downstairs(location);
                case Agilas.EntityType.UPSTAIRS:
                    return new Upstairs(location);
                default:
                    throw new Exception("An undefined int case was passed into the EntityFactory.");
            }
        }
    }
}