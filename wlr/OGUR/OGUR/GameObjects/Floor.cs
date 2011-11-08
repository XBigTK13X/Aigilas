using OGUR.Collision;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class Floor : GameplayObject
    {
        public Floor(Point2 location)
        {
            Initialize(location, SpriteType.FLOOR, GameObjectType.FLOOR,Depth.Floor);
        }
    }
}