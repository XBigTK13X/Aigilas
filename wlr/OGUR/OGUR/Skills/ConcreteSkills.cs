using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Skills;
using OGUR.Sprites;
using OGUR.Statuses;
using OGUR.Items;

namespace OGUR.Skills
{
    public class NoSkill:ISkill
    {
        public NoSkill():base(SkillId.NO_SKILL,AnimationType.NONE){}
        public override void Activate(ICreature source){}
        public override void Affect(GameplayObject target){}
        public override void Affect(ICreature target){}
    }
    public class AbsorbSkill : ISkill
    {
        public AbsorbSkill(): base(SkillId.ABSORB, AnimationType.RANGED)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target){base.Affect(target);}
    }
    public class AcidDripSkill : ISkill
    {
        public AcidDripSkill() : base(SkillId.ACID_DRIP, AnimationType.STATIONARY) { StartOffCenter = true; AddCost(StatType.MANA, 10); Add(Elements.WATER); }
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            target.ApplyDamage(20, m_source);
        }
    }
    public class AcidNozzleSkill : ISkill
    {
        public AcidNozzleSkill() : base(SkillId.ACID_NOZZLE, AnimationType.STATIONARY) { AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            CreatureFactory.CreateMinion(m_implementationId, source);
        }
    }
    public class CalvarySkill : ISkill
    {
        public CalvarySkill()
            : base(SkillId.CAVALRY, AnimationType.SELF)
        { Add(Elements.DARK,Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ColdShoulderSkill : ISkill
    {
        public ColdShoulderSkill()
            : base(SkillId.COLD_SHOULDER, AnimationType.RANGED)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class CombustSkill : ISkill
    {
        public CombustSkill()
            : base(SkillId.COMBUST, AnimationType.RANGED)
        { Add(Elements.AIR,Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ConfusionSkill : ISkill
    {
        public ConfusionSkill()
            : base(SkillId.CONFUSION, AnimationType.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        { base.Affect(target);
            StatusFactory.Apply(target, Status.Confusion);
        }
    }
    public class DartSkill : ISkill
    {
        public DartSkill() : base(SkillId.DART, AnimationType.RANGED) { AddCost(StatType.MANA, 2); Add(Elements.DARK); }
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            StatusFactory.Apply(target, Status.Poison);
            target.ApplyDamage(5,m_source);
        }
    }    
    public class ElectrifySkill : ISkill
    {
        public ElectrifySkill()
            : base(SkillId.ELECTRIFY, AnimationType.RANGED)
        { Add(Elements.AIR); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class EnvenomSkill : ISkill
    {
        public EnvenomSkill()
            : base(SkillId.ENVENOM, AnimationType.RANGED)
        { Add(Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class FireballSkill:ISkill
    {
        public FireballSkill() : base(SkillId.FIREBALL,AnimationType.RANGED)
        { Add(Elements.FIRE);AddCost(StatType.MANA,10);}

        public override void Affect(ICreature target)
        {
            base.Affect(target);
            target.ApplyDamage(20,m_source);
        }
    }
    public class FlameHammerSkill : ISkill
    {
        public FlameHammerSkill()
            : base(SkillId.FLAME_HAMMER, AnimationType.ROTATE)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        { base.Affect(target);
        target.ApplyDamage(3f, m_source);
        }
    }
    public class FloorSpikesSkill : ISkill
    {
        public FloorSpikesSkill() : base(SkillId.FLOOR_SPIKES, AnimationType.STATIONARY, float.MaxValue, true) { AddCost(StatType.MANA, 20); Add(Elements.EARTH); }
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            target.ApplyDamage(80,m_source);
        }
    }
    public class ForgetSkill : ISkill
    {
        public ForgetSkill()
            : base(SkillId.FORGET_SKILL, AnimationType.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class GushSkill : ISkill
    {
        public GushSkill()
            : base(SkillId.GUSH, AnimationType.RANGED)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class HorderSkill : ISkill
    {
        public HorderSkill()
            : base(SkillId.HORDER, AnimationType.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class HorrifySkill : ISkill
    {
        public HorrifySkill()
            : base(SkillId.HORRIFY, AnimationType.RANGED)
        { Add(Elements.DARK,Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class MagicMapSkill : ISkill
    {
        public MagicMapSkill()
            : base(SkillId.MAGIC_MAP, AnimationType.RANGED)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ManaUpSkill : ISkill
    {
        public ManaUpSkill()
            : base(SkillId.MANA_UP, AnimationType.RANGED)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class MimicSkill : ISkill
    {
        public MimicSkill()
            : base(SkillId.MIMIC, AnimationType.RANGED)
        { Add(Elements.DARK); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class MutinySkill : ISkill
    {
        public MutinySkill()
            : base(SkillId.MUTINY, AnimationType.RANGED)
        { Add(Elements.MENTAL,Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class RegenAllSkill : ISkill
    {
        public RegenAllSkill()
            : base(SkillId.REGEN_ALL, AnimationType.SELF)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); StatusFactory.Apply(target, Status.Regen); }
    }
    public class RemoteMineSkill : ISkill
    {
        public RemoteMineSkill() : base(SkillId.REMOTE_MINE, AnimationType.STATIONARY) { AddCost(StatType.MANA, 10); Add(Elements.FIRE); }
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            Buff(target);
        }
    }
    public class SoulCrushSkill : ISkill
    {
        public SoulCrushSkill()
            : base(SkillId.SOUL_CRUSH, AnimationType.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class SoulReinforcementSkill : ISkill
    {
        private GenericItem fodder;
        public SoulReinforcementSkill()
            : base(SkillId.SOUL_REINFORCEMENT, AnimationType.SELF)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        { base.Affect(target);
            fodder = target.GetNonEquippedItem();
            target.Drop(fodder);
            GameplayObjectManager.RemoveObject(fodder);
        }
    }
    public class SpawnItemSkill : ISkill
    {
        public SpawnItemSkill()
            : base(SkillId.SPAWN_ITEM, AnimationType.RANGED)
        { Add(Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class SpeedUpSkill : ISkill
    {
        public SpeedUpSkill()
            : base(SkillId.SPEED_UP, AnimationType.RANGED)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class StealItemSkill : ISkill
    {
        public StealItemSkill()
            : base(SkillId.STEAL_ITEM, AnimationType.RANGED)
        { Add(Elements.WATER,Elements.AIR); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class StrengthUpSkill : ISkill
    {
        public StrengthUpSkill()
            : base(SkillId.STRENGTH_UP, AnimationType.SELF)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); StatusFactory.Apply(target, Status.StrengthUp); }
    }
    public class ThrowItemSkill : ISkill
    {
        public ThrowItemSkill()
            : base(SkillId.THROW_ITEM, AnimationType.RANGED)
        { Add(Elements.AIR); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ValedictorianSkill : ISkill
    {
        public ValedictorianSkill()
            : base(SkillId.VALEDICTORIAN, AnimationType.RANGED)
        { Add(Elements.MENTAL,Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class VaporImplantSkill : ISkill
    {
        public VaporImplantSkill() : base(SkillId.VAPOR_IMPLANT, AnimationType.RANGED) { AddCost(StatType.MANA, 10); Add(Elements.PHYSICAL,Elements.AIR); }
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            Buff(target);
        }
    }
    public class VenomFistSkill : ISkill
    {
        public VenomFistSkill()
            : base(SkillId.VENOM_FIST, AnimationType.RANGED)
        { Add(Elements.DARK); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class WallPunchSkill : ISkill
    {
        public WallPunchSkill()
            : base(SkillId.WALL_PUNCH, AnimationType.RANGED)
        { Add(Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class WeakKneesSkill : ISkill
    {
        public WeakKneesSkill()
            : base(SkillId.WEAK_KNEEES, AnimationType.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
}