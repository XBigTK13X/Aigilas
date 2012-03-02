using OGUR.Creatures;
using SPX.Entities;
using SPX.Core;

namespace OGUR.Entities
{
    public static class Extensions
    {
        public static ICreature IsCreature(this Entity entity)
        {
            if (entity.EntityType() == EntityType.CREATURE)
            {
                return entity as ICreature;
            }
            return null;
        }

        public static void PerformInteraction(this Entity creature)
        {
            creature.SetInteracting(false);
            InputManager.Lock(Commands.Confirm, ((ICreature)creature).GetPlayerIndex());
        }
    }
}
