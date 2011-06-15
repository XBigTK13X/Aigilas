using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class Floor : GameplayObject
    {
        public Floor(int gridX, int gridY)
        {
            Initialize(gridX, gridY, SpriteType.FLOOR, GameObjectType.FLOOR);
        }
    }
}