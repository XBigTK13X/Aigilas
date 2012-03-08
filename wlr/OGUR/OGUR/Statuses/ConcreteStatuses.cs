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
        public const int ManaUp = 7;
        public const int SpeedUp = 8;
        public const int Electrify = 9;
        public const int Zap = 10;
        public const int PoisonOneHit = 11;
        public const int DefenseUp = 12;
        public const int ColdShoulder = 13;
        public const int Burn = 14;
        public const int Flee = 15;
        public const int Berserk = 16;
        public const int RandomBuff = 17;
        public const int Hord = 18;
        public const int LockSkillCycle = 19;
        public const int WeakenStrength = 20;
        public const int SelfMutilation = 21;
        public const int PreventMentalUsage = 22;
        public const int PreventRegeneration = 23;
        public const int PreventLightUsage = 24;
        public const int PreventDarkUsage = 25;
        public const int FragileItems = 26;
        public const int IntDown = 27;
        public const int SoakingWet = 28;
        public const int HealReflect = 29;
        public const int Mute = 30;
        public const int SlowDown = 31;
        public const int WeakMuscles = 32;
        public const int Blind = 33;

        public static readonly int[] Values =
        {
            Poison,
            Regen,
            StrengthUp,
            Confusion,
            WeakKnees,
            VenomFist,
            Mutiny,
            ManaUp,
            SpeedUp,
            Electrify,
            Zap,
            PoisonOneHit,
            DefenseUp,
            ColdShoulder,
            Burn,
            Flee,
            Berserk,
            RandomBuff,
            Hord,
            LockSkillCycle,
            WeakenStrength,
            SelfMutilation,
            PreventMentalUsage,
            PreventRegeneration,
            PreventLightUsage,
            PreventDarkUsage,
            FragileItems,
            IntDown,
            SoakingWet,
            HealReflect,
            Mute,
            SlowDown,
            WeakMuscles,
            Blind
        };
    }

    public class ConfusionStatus : IStatus
    {
        private int previousStrategy;
        public ConfusionStatus(ICreature target) : base(false, false, target) { _hitAnything = true; }
        public override void Setup()
        {
            base.Setup();
            previousStrategy = StrategyFactory.GetId(_target.GetStrategyType());
            _target.SetStrategy(StrategyFactory.Create(Strategy.Confused,_target));
        }
        public override void Cleanup()
        {
            base.Cleanup();
            _target.SetStrategy(StrategyFactory.Create(previousStrategy,_target));
        }
    }
    public class MutinyStatus : IStatus
    {
        private int previousStrategy;
        private StatBuff buff = new StatBuff(StatType.MOVE_COOL_DOWN, 10);
        public MutinyStatus(ICreature target) : base(false, false, target) { _hitAnything = true; }
        public override void Setup()
        {
            base.Setup();
            previousStrategy = StrategyFactory.GetId(_target.GetStrategyType());
            _target.SetStrategy(StrategyFactory.Create(Strategy.Mutiny, _target));
        }
        public override void Cleanup()
        {
            base.Cleanup();
            _target.SetStrategy(StrategyFactory.Create(previousStrategy, _target));
        }
    }
    public class FleeStatus : IStatus
    {
        private int previousStrategy;
        public FleeStatus(ICreature target) : base(false, false, target) { _hitAnything = true; }
        public override void Setup()
        {
            base.Setup();
            previousStrategy = StrategyFactory.GetId(_target.GetStrategyType());
            _target.SetStrategy(StrategyFactory.Create(Strategy.Flee, _target));
        }
        public override void Cleanup()
        {
            base.Cleanup();
            _target.SetStrategy(StrategyFactory.Create(previousStrategy, _target));
        }
    }
    public class PoisonStatus:IStatus
    {
        public PoisonStatus(ICreature target): base(false, false,target){}
        public override void Update()
        {
            base.Update();
            _target.ApplyDamage(2.1f);
        }
    }

    public class RegenStatus : IStatus
    {
        public RegenStatus(ICreature target) : base(false, false, target) { }
        public override void Update()
        {
            base.Update();
            _target.ApplyDamage(-1f);
        }
    }

    public class StrengthUpStatus : IStatus
    {
        public StrengthUpStatus(ICreature target) : base(false, false, target)
        {
            _buff = new StatBuff(StatType.STRENGTH, 10f);
            Setup();
        }
    }

    public class VenomFistStatus : IStatus
    {
        public VenomFistStatus(ICreature target)
            : base(false, false, target)
        {
            Add(Status.Poison,StatusComponent.Contagion);
        }
    }

    public class WeakKneesStatus : IStatus
    {
        public WeakKneesStatus(ICreature target) : base(true, true, target) { }
    }

    public class ManaUpStatus : IStatus
    {
        public ManaUpStatus(ICreature target) : base(false, false, target) 
        {
            _buff = new StatBuff(StatType.MANA, 20f);
            Setup();
        }
    }
    public class SpeedUpStatus: IStatus
    {
        public SpeedUpStatus(ICreature target) : base(false, false, target)
        {
            _buff = new StatBuff(StatType.MOVE_COOL_DOWN, 5f);
            Setup();
        }
    }
    public class ElectrifyStatus: IStatus
    {
        public ElectrifyStatus(ICreature target)
            : base(false, false, target)
        {
            Add(Status.Zap,StatusComponent.Passive );
        }
    }
    public class ZapStatus : IStatus
    {
        public ZapStatus(ICreature target)
            : base(false, false, target)
        {}
        public override void Setup()
        {
            base.Setup();
            _target.ApplyDamage(10);
            _isActive = false;
        }
    }
    public class PoisonOneHitStatus : IStatus
    {
        public PoisonOneHitStatus(ICreature target)
            : base(false, false, target)
        {
            Add(Status.Poison,StatusComponent.Contagion);
        }
        public override void Update()
        {
            base.Update();
            if (_wasPassed)
            {
                _isActive = false;
            }
        }
    }
    public class BurnOneHitStatus : IStatus
    {
        public BurnOneHitStatus(ICreature target)
            : base(false, false, target)
        {
            Add(Status.Burn, StatusComponent.Contagion);
        }
        public override void Update()
        {
            base.Update();
            if (_wasPassed)
            {
                _isActive = false;
            }
        }
    }
    public class DefenseUpStatus : IStatus
    {
        public DefenseUpStatus(ICreature target)
        : base(false, false, target)
        {
            _buff = new StatBuff(StatType.DEFENSE, 10);
            Setup();
        }
    }
    public class BurnStatus : IStatus
    {
        public BurnStatus(ICreature target) : base(true, false, target) { }
        public override void Update()
        {
            base.Update();
            _target.ApplyDamage(1.0f);
        }
    }
    public class BerserkStatus : IStatus
    {
        public BerserkStatus(ICreature target) : base(false, false, target) 
        {
            Add(Status.RandomBuff, StatusComponent.KillReward);
        }
    }
    public class RandomBuffStatus : IStatus
    {
        private Random rand = new Random();
        public RandomBuffStatus(ICreature target)
        : base(false, false, target)
        {
            _buff = new StatBuff(StatType.Values[rand.Next(0,3)], 10);
            Setup();
        }
    }
    public class HordStatus : IStatus
    {
        public HordStatus(ICreature target)
            : base(false, false, target)
        {
            _buff = new StatBuff(StatType.STRENGTH, target.GetInventoryCount());
            Setup();
        }
    }
    public class LockSkillCycleStatus : IStatus
    {
        public LockSkillCycleStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class WeakenStrengthStatus : IStatus
    {
        public WeakenStrengthStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class SelfMutilationStatus : IStatus
    {
        public SelfMutilationStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class PreventMentalUsageStatus : IStatus
    {
        public PreventMentalUsageStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class PreventRegenerationStatus : IStatus
    {
        public PreventRegenerationStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class PreventLightUsageStatus : IStatus
    {
        public PreventLightUsageStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class PreventDarkUsageStatus : IStatus
    {
        public PreventDarkUsageStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class FragileItemsStatus : IStatus
    {
        public FragileItemsStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class IntDownStatus : IStatus
    {
        public IntDownStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class SoakingWetStatus : IStatus
    {
        public SoakingWetStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class HealReflectStatus : IStatus
    {
        public HealReflectStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class MuteStatus : IStatus
    {
        public MuteStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class SlowDownStatus : IStatus
    {
        public SlowDownStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class WeakMusclesStatus : IStatus
    {
        public WeakMusclesStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }
    public class BlindStatus : IStatus
    {
        public BlindStatus(ICreature target)
            : base(false, false, target)
        {
            throw new NotImplementedException();
        }
    }

}
