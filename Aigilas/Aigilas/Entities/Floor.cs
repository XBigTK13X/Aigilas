using SPX.Entities;
using Aigilas.Management;
using SPX.Core;
using Aigilas.Entities;

namespace Aigilas.Entities
{
    public class Floor : Entity
    {
        public Floor(Point2 location)
        {
            Initialize(location, SpriteType.FLOOR, Aigilas.EntityType.FLOOR,ZDepth.Floor);
        }
    }
}