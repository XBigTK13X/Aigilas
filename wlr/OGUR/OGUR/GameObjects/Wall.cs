using OGUR.Collision;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class Wall : GameplayObject
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, GameObjectType.WALL);
            m_isBlocking = true;
        }
    }
}