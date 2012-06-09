using Agilas.Creatures;
using SPX.Entities;
using SPX.Core;
using System.Collections.Generic;

namespace Agilas.Entities
{
    public static class Extensions
    {
        public static ICreature IsCreature(this IEntity entity)
        {
            if (entity.GetEntityType() == Agilas.EntityType.ACTOR)
            {
                return entity as ICreature;
            }
            return null;
        }
    }
}
