using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Skills;
using OGUR.Sprites;

namespace OGUR.Skills
{
    public class NoSkill:ISkill
    {
        public NoSkill():base(SkillId.NO_SKILL,Skill.Animation.NONE){}
        public override void Activate(ICreature source){}
        public override void Affect(GameplayObject target){}
        public override void Affect(ICreature target){}
    }
    public class AbsorbSkill : ISkill
    {
        public AbsorbSkill(): base(SkillId.ABSORB, Skill.Animation.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target){base.Affect(target);}
    }
    public class AcidDripSkill : ISkill
    {
        public AcidDripSkill() : base(SkillId.ACID_DRIP, Skill.Animation.STATIONARY) { StartOffCenter = true; AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            target.ApplyDamage(20);
        }
    }
    public class AcidNozzleSkill : ISkill
    {
        public AcidNozzleSkill() : base(SkillId.ACID_NOZZLE, Skill.Animation.STATIONARY) { AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            CreatureFactory.CreateMinion(m_implementationId, source);
        }
    }
    public class CalvarySkill : ISkill
    {
        public CalvarySkill()
            : base(SkillId.CAVALRY, Skill.Animation.SELF)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ColdShoulderSkill : ISkill
    {
        public ColdShoulderSkill()
            : base(SkillId.COLD_SHOULDER, Skill.Animation.RANGED)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class CombustSkill : ISkill
    {
        public CombustSkill()
            : base(SkillId.COMBUST, Skill.Animation.RANGED)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ConfusionSkill : ISkill
    {
        public ConfusionSkill()
            : base(SkillId.CONFUSION, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class DartSkill : ISkill
    {
        public DartSkill() : base(SkillId.DART, Skill.Animation.RANGED) { AddCost(StatType.MANA, 2); Add(Elements.FIRE, Elements.EARTH); }
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            target.ApplyDamage(5);
        }
    }
    public class DropRateUpSkill : ISkill
    {
        public DropRateUpSkill()
            : base(SkillId.DROP_RATE_UP, Skill.Animation.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ElectrifySkill : ISkill
    {
        public ElectrifySkill()
            : base(SkillId.ELECTRIFY, Skill.Animation.RANGED)
        { Add(Elements.AIR); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class EnvenomSkill : ISkill
    {
        public EnvenomSkill()
            : base(SkillId.ENVENOM, Skill.Animation.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class FireballSkill:ISkill
    {
        public FireballSkill() : base(SkillId.FIREBALL,Skill.Animation.RANGED)
        { Add(Elements.FIRE);AddCost(StatType.MANA,10);}

        public override void Affect(ICreature target)
        {
            base.Affect(target);
            target.ApplyDamage(20,target);
        }
    }
    public class FlameHammerSkill : ISkill
    {
        public FlameHammerSkill()
            : base(SkillId.FLAME_HAMMER, Skill.Animation.RANGED)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class FloorSpikesSkill : ISkill
    {
        public FloorSpikesSkill(): base(SkillId.FLOOR_SPIKES, Skill.Animation.STATIONARY,float.MaxValue,true){AddCost(StatType.MANA, 20);}
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            target.ApplyDamage(80);
        }
    }
    public class ForgetSkill : ISkill
    {
        public ForgetSkill()
            : base(SkillId.FORGET_SKILL, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class GushSkill : ISkill
    {
        public GushSkill()
            : base(SkillId.GUSH, Skill.Animation.RANGED)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class HorderSkill : ISkill
    {
        public HorderSkill()
            : base(SkillId.HORDER, Skill.Animation.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class HorrifySkill : ISkill
    {
        public HorrifySkill()
            : base(SkillId.HORRIFY, Skill.Animation.RANGED)
        { Add(Elements.DARK); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class MagicMapSkill : ISkill
    {
        public MagicMapSkill()
            : base(SkillId.MAGIC_MAP, Skill.Animation.RANGED)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ManaUpSkill : ISkill
    {
        public ManaUpSkill()
            : base(SkillId.MANA_UP, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class MimicSkill : ISkill
    {
        public MimicSkill()
            : base(SkillId.MIMIC, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class MutinySkill : ISkill
    {
        public MutinySkill()
            : base(SkillId.MUTINY, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class RegenAllSkill : ISkill
    {
        public RegenAllSkill()
            : base(SkillId.REGEN_ALL, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class RemoteMineSkill : ISkill
    {
        public RemoteMineSkill(): base(SkillId.REMOTE_MINE, Skill.Animation.STATIONARY){AddCost(StatType.MANA, 10);}
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            Buff(target);
        }
    }
    public class SoulCrushSkill : ISkill
    {
        public SoulCrushSkill()
            : base(SkillId.SOUL_CRUSH, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class SoulReinforcementSkill : ISkill
    {
        public SoulReinforcementSkill()
            : base(SkillId.SOUL_REINFORCEMENT, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class SpeedUpSkill : ISkill
    {
        public SpeedUpSkill()
            : base(SkillId.SPEED_UP, Skill.Animation.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class StealItemSkill : ISkill
    {
        public StealItemSkill()
            : base(SkillId.STEAL_ITEM, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class StrengthUpSkill : ISkill
    {
        public StrengthUpSkill()
            : base(SkillId.STRENGTH_UP, Skill.Animation.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ThrowItemSkill : ISkill
    {
        public ThrowItemSkill()
            : base(SkillId.THROW_ITEM, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class ValedictorianSkill : ISkill
    {
        public ValedictorianSkill()
            : base(SkillId.VALEDICTORIAN, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class VaporImplantSkill : ISkill
    {
        public VaporImplantSkill(): base(SkillId.VAPOR_IMPLANT, Skill.Animation.RANGED){AddCost(StatType.MANA, 10);}
        public override void Affect(ICreature target)
        {
            base.Affect(target);
            Buff(target);
        }
    }
    public class VenomFistSkill : ISkill
    {
        public VenomFistSkill()
            : base(SkillId.VENOM_FIST, Skill.Animation.RANGED)
        { Add(Elements.DARK); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class WallPunchSkill : ISkill
    {
        public WallPunchSkill()
            : base(SkillId.WALL_PUNCH, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
    public class WeakKneesSkill : ISkill
    {
        public WeakKneesSkill()
            : base(SkillId.WEAK_KNEEES, Skill.Animation.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { base.Affect(target); }
    }
}