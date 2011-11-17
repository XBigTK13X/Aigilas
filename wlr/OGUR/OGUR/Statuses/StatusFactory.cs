using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Statuses
{
    class StatusFactory
    {
        public static void Apply(ICreature target, int statusId)
        {
            switch (statusId)
            {
                case Status.Poison: target.AddStatus(new PoisonStatus(target)); break;
                default:
                    throw new Exception(String.Format("An undefined statusId {0} was passed StatusFactory.Apply.", statusId));
            }
        }
    }
}
