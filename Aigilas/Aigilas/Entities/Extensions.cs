using Aigilas.Creatures;
using SPX.Entities;
using SPX.Core;
using System.Collections.Generic;

namespace Aigilas.Entities
{
    public static class Extensions
    {
        public static ICreature IsCreature(this IEntity entity)
        {
            if (entity.GetEntityType() == Aigilas.EntityType.ACTOR)
            {
                return entity as ICreature;
            }
            return null;
        }
    }
}
