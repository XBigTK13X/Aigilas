using SPX.Core;
using Aigilas.Management;
using SPX.Entities;

namespace Aigilas.Entities
{
    public class Wall : Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, Aigilas.EntityType.WALL,ZDepth.Wall);
            _isBlocking = true;
        }
    }
}