using OGUR.Collision;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class Wall : Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, GameObjectType.WALL,Depth.Wall);
            m_isBlocking = true;
        }
    }
}