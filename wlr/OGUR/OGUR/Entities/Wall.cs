using SPX.Core;
using OGUR.Management;
using SPX.Entities;
using System;

namespace OGUR.Entities
{
    [Serializable()]
    public class Wall : Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, OGUR.EntityType.WALL,ZDepth.Wall);
            _isBlocking = true;
        }
    }
}