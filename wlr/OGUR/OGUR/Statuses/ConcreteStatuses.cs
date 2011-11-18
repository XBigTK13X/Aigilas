using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Statuses
{
    public class Status
    {
        public const int Poison = 0;
        public static readonly int[] Values =
        {
            Poison
        };
    }

    public class PoisonStatus:IStatus
    {
        public PoisonStatus(ICreature target)
            : base(false, false,target)
        {

        }
        public override void Update()
        {
            base.Update();
            m_target.ApplyDamage(2.1f);
        }
    }
}
