using SPX.Core;
using Agilas.Management;
using SPX.Entities;

namespace Agilas.Entities
{
    public class Wall : Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, Agilas.EntityType.WALL,ZDepth.Wall);
            _isBlocking = true;
        }
    }
}