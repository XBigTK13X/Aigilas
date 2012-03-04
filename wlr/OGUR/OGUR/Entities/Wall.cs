using SPX.Core;
using OGUR.Management;
using SPX.Entities;

namespace OGUR.Entities
{
    public class Wall : Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, OGUR.EntityType.WALL,ZDepth.Wall);
            _isBlocking = true;
        }
    }
}