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
                case Status.Confusion: target.AddStatus(new ConfusionStatus(target)); break;
                case Status.Mutiny: target.AddStatus(new MutinyStatus(target)); break;
                case Status.Poison: target.AddStatus(new PoisonStatus(target)); break;
                case Status.Regen: target.AddStatus(new RegenStatus(target)); break;
                case Status.StrengthUp: target.AddStatus(new StrengthUpStatus(target)); break;                
                case Status.VenomFist: target.AddStatus(new VenomFistStatus(target)); break;
                case Status.WeakKnees: target.AddStatus(new WeakKneesStatus(target)); break;
                case Status.SpeedUp: target.AddStatus(new SpeedUpStatus(target)); break;
                case Status.ManaUp: target.AddStatus(new ManaUpStatus(target)); break;
                case Status.Electrify: target.AddStatus(new ElectrifyStatus(target)); break;
                case Status.Zap: target.AddStatus(new ZapStatus(target)); break;
                default:
                    throw new Exception(String.Format("An undefined statusId {0} was passed StatusFactory.Apply.", statusId));
            }
        }
    }
}
