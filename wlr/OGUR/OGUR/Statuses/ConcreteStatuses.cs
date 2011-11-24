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
        public const int Regen = 1;
        public const int StrengthUp = 2;
        public static readonly int[] Values =
        {
            Poison,
            Regen,
            StrengthUp
        };
    }

    public class PoisonStatus:IStatus
    {
        public PoisonStatus(ICreature target): base(false, false,target){}
        public override void Update()
        {
            base.Update();
            m_target.ApplyDamage(2.1f);
        }
    }

    public class RegenStatus : IStatus
    {
        public RegenStatus(ICreature target) : base(false, false, target) { }
        public override void Update()
        {
            base.Update();
            m_target.ApplyDamage(-1f);
        }
    }

    public class StrengthUpStatus : IStatus
    {
        private StatBuff buff = new StatBuff(StatType.STRENGTH, 10f);
        public StrengthUpStatus(ICreature target) : base(false, false, target) { }
        public override void Setup()
        {
            base.Setup();
            m_target.AddBuff(buff);
        }
        public override void Cleanup()
        {
            base.Cleanup();
            m_target.AddBuff(buff);
        }
    }
}
