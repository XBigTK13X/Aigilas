using OGUR.Collision;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class Floor : Entity
    {
        public Floor(Point2 location)
        {
            Initialize(location, SpriteType.FLOOR, GameObjectType.FLOOR,Depth.Floor);
        }
    }
}