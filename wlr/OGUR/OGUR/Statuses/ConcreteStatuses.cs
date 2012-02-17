using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Strategies;

namespace OGUR.Statuses
{
    public class Status
    {
        public const int Poison = 0;
        public const int Regen = 1;
        public const int StrengthUp = 2;
        public const int Confusion = 3;
        public const int WeakKnees = 4;
        public const int VenomFist = 5;
        public const int Mutiny = 6;

        public static readonly int[] Values =
        {
            Poison,
            Regen,
            StrengthUp,
            Confusion,
            WeakKnees,
            VenomFist,
            Mutiny
        };
    }

    public class ConfusionStatus : IStatus
    {
        private int previousStrategy;
        public ConfusionStatus(ICreature target) : base(false, false, target) { m_hitAnything = true; }
        public override void Setup()
        {
            base.Setup();
            previousStrategy = StrategyFactory.GetId(m_target.GetStrategyType());
            m_target.SetStrategy(StrategyFactory.Create(Strategy.Confused,m_target));
        }
        public override void Cleanup()
        {
            base.Cleanup();
            m_target.SetStrategy(StrategyFactory.Create(previousStrategy,m_target));
        }
    }
    public class MutinyStatus : IStatus
    {
        private int previousStrategy;
        private StatBuff buff = new StatBuff(StatType.MOVE_COOL_DOWN, 10);
        public MutinyStatus(ICreature target) : base(false, false, target) { m_hitAnything = true; }
        public override void Setup()
        {
            base.Setup();
            previousStrategy = StrategyFactory.GetId(m_target.GetStrategyType());
            m_target.SetStrategy(StrategyFactory.Create(Strategy.Mutiny, m_target));
        }
        public override void Cleanup()
        {
            base.Cleanup();
            m_target.SetStrategy(StrategyFactory.Create(previousStrategy, m_target));
        }
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
    }

    public class VenomFistStatus : IStatus
    {
        public VenomFistStatus(ICreature target)
            : base(false, false, target)
        {
            m_contagions.Add(Status.Poison);
        }
    }

    public class WeakKneesStatus : IStatus
    {
        public WeakKneesStatus(ICreature target) : base(true, true, target) { }
    }
}
