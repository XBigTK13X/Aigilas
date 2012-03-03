using OGUR.Creatures;
using SPX.Entities;
using SPX.Core;
using System.Collections.Generic;

namespace OGUR.Entities
{
    public static class Extensions
    {
        public static ICreature IsCreature(this IEntity entity)
        {
            if (entity.GetEntityType() == OGUR.EntityType.ACTOR)
            {
                return entity as ICreature;
            }
            return null;
        }
    }
}
