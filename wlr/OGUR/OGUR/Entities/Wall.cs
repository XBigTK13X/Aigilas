using SPX.Core;
using SPX.Sprites;
using SPX.Entities;

namespace OGUR.Entities
{
    public class Wall : Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, EntityType.WALL,ZZDepth.Wall);
            m_isBlocking = true;
        }
    }
}