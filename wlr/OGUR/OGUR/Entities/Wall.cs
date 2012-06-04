using SPX.Core;
using OGUR.Management;
using SPX.Entities;
using System;
using System.Runtime.Serialization;

namespace OGUR.Entities
{
    [Serializable()]
    public class Wall : Entity
    {
        public Wall(SerializationInfo info, StreamingContext context):base(info, context){}

        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, OGUR.EntityType.WALL,ZDepth.Wall);
            _isBlocking = true;
        }
    }
}