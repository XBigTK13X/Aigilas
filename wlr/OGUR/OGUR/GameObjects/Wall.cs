using OGUR.Collision;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class Wall : GameplayObject
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, GameObjectType.WALL,.1f);
            m_isBlocking = true;
        }
    }
}