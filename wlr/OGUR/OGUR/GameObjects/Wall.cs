using OGUR.Sprites;

namespace OGUR.GameObjects
{
    internal class Wall : GameplayObject
    {
        public Wall(int x, int y)
        {
            Initialize(x, y, SpriteType.WALL, GameObjectType.WALL);
            m_isBlocking = true;
        }
    }
}