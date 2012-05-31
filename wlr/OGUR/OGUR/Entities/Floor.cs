using SPX.Entities;
using OGUR.Management;
using SPX.Core;
using OGUR.Entities;
using System;

namespace OGUR.Entities
{
    [Serializable()]
    public class Floor : Entity
    {
        public Floor(Point2 location)
        {
            Initialize(location, SpriteType.FLOOR, OGUR.EntityType.FLOOR,ZDepth.Floor);
        }
    }
}