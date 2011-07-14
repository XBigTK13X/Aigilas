using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    public class Downstairs : GameplayObject
    {
        public Downstairs(int x, int y)
        {
            Initialize(x, y, SpriteType.DOWNSTAIRS, GameObjectType.DOWNSTAIRS);
        }
        public Location GetTargetLocation()
        {
            return Location.Depths;
        }
    }
}