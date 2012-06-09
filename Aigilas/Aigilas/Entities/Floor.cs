using SPX.Entities;
using Agilas.Management;
using SPX.Core;
using Agilas.Entities;

namespace Agilas.Entities
{
    public class Floor : Entity
    {
        public Floor(Point2 location)
        {
            Initialize(location, SpriteType.FLOOR, Agilas.EntityType.FLOOR,ZDepth.Floor);
        }
    }
}