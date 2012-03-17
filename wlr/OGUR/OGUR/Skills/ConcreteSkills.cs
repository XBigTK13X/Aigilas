using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Entities;
using OGUR.Skills;
using SPX.Sprites;
using OGUR.Statuses;
using OGUR.Items;
using SPX.Entities;
using SPX.Core;

namespace OGUR.Skills
{
    public class NoSkill : ISkill
    {
        public NoSkill() : base(SkillId.NO_SKILL, AnimationType.NONE) { }
        public override void Activate(ICreature source) { }
        public override void Affect(IEntity target) { }
        public override void Affect(ICreature target) { }
    }
    public class AbsorbSkill : ISkill
    {
        public AbsorbSkill()
            : base(SkillId.ABSORB, AnimationType.RANGED)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        {
            target.ApplyDamage(10,_source);
            _source.ApplyDamage(-10);
            target.ApplyDamage(10, _source, true, StatType.MANA);
            _source.ApplyDamage(-10, _source, true, StatType.MANA);
        }
    }
    public class AcidDripSkill : ISkill
    {
        public AcidDripSkill() : base(SkillId.ACID_DRIP, AnimationType.STATIONARY) { StartOffCenter = true; AddCost(StatType.MANA, 10); Add(Elements.WATER); }
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(20, _source);
        }
    }
    public class AcidNozzleSkill : ISkill
    {
        public AcidNozzleSkill() : base(SkillId.ACID_NOZZLE, AnimationType.STATIONARY) { AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            CreatureFactory.CreateMinion(_implementationId, source);
        }
    }
    public class CavalrySkill : ISkill
    {
        public CavalrySkill()
            : base(SkillId.CAVALRY, AnimationType.SELF)
        { Add(Elements.DARK, Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            ApplyToPlayers(Status.DefenseUp);
        }
    }
    public class ColdShoulderSkill : ISkill
    {
        public ColdShoulderSkill()
            : base(SkillId.COLD_SHOULDER, AnimationType.SELF)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            ApplyToPlayers(Status.ColdShoulder);
        }
    }
    public class CombustSkill : ISkill
    {
        private const int CombustDistance = 1;
        public CombustSkill()
            : base(SkillId.COMBUST, AnimationType.RANGED)
        { Add(Elements.AIR, Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        {
            target.ApplyDamage(10,_source);
            if (!target.IsActive())
            {
                var targets = EntityManager.GetActorsSurrounding(target.GetLocation(), CombustDistance);
                for(int ii = 0; ii < targets.Count;ii++)
                {
                    StatusFactory.Apply(targets[ii] as ICreature, Status.Burn);
                }
            }
        }
    }
    public class ConfusionSkill : ISkill
    {
        public ConfusionSkill()
            : base(SkillId.CONFUSION, AnimationType.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Confusion);
        }
    }
    public class DartSkill : ISkill
    {
        public DartSkill() : base(SkillId.DART, AnimationType.RANGED) { AddCost(StatType.MANA, 10); Add(Elements.DARK); }
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Poison); target.ApplyDamage(5, _source);
        }
    }
    public class DartTrapSkill : ISkill
    {
        public DartTrapSkill() : base(SkillId.DART_TRAP, AnimationType.RANGED) { AddCost(StatType.MANA, 10); Add(Elements.DARK); }
        public override void Cleanup(Entity target,SkillEffect source)
        {
            CreatureFactory.CreateMinion(_implementationId, _source,source,target.GetLocation());
        }
    }
    public class ElectrifySkill : ISkill
    {
        public ElectrifySkill()
            : base(SkillId.ELECTRIFY, AnimationType.SELF)
        { Add(Elements.AIR); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            StatusFactory.Apply(source, Status.Electrify);
        }
    }
    public class EnvenomSkill : ISkill
    {
        public EnvenomSkill()
            : base(SkillId.ENVENOM, AnimationType.SELF)
        { Add(Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void  Activate(ICreature source)
        {
 	        base.Activate(source);
            ApplyToPlayers(Status.PoisonOneHit);
        }
    }
    public class ExplodeSkill : ISkill
    {
        public ExplodeSkill()
            : base(SkillId.EXPLODE, AnimationType.CLOUD)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 0); }
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(10,_source,true);
        }
    }
    public class FireballSkill : ISkill
    {
        public FireballSkill()
            : base(SkillId.FIREBALL, AnimationType.RANGED)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }

        public override void Affect(ICreature target)
        {
            target.ApplyDamage(20, _source);
        }
    }
    public class FlameHammerSkill : ISkill
    {
        public FlameHammerSkill()
            : base(SkillId.FLAME_HAMMER, AnimationType.ROTATE)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(3f, _source);
        }
    }
    public class FloorSpikesSkill : ISkill
    {
        public FloorSpikesSkill() : base(SkillId.FLOOR_SPIKES, AnimationType.STATIONARY, float.MaxValue, true) { AddCost(StatType.MANA, 20); Add(Elements.EARTH); }
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(80, _source);
        }
    }
    public class ForgetSkill : ISkill
    {
        public ForgetSkill()
            : base(SkillId.FORGET_SKILL, AnimationType.SELF)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            _source.RemoveLeastUsedSkill();
        }
    }
    public class GushSkill : ISkill
    {
        public GushSkill()
            : base(SkillId.GUSH, AnimationType.RANGED)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        {
            target.ApplyDamage(10, _source, true);
        }
    }
    public class HorderSkill : ISkill
    {
        public HorderSkill()
            : base(SkillId.HORDER, AnimationType.SELF)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            StatusFactory.Apply(source, Status.Hord);
        }
    }
    public class HorrifySkill : ISkill
    {
        public HorrifySkill()
            : base(SkillId.HORRIFY, AnimationType.RANGED)
        { Add(Elements.DARK, Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        {
            StatusFactory.Apply(target, Status.Flee);
        }
    }
    public class MagicMapSkill : ISkill
    {
        public MagicMapSkill()
            : base(SkillId.MAGIC_MAP, AnimationType.RANGED)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { }
    }
    public class ManaUpSkill : ISkill
    {
        public ManaUpSkill()
            : base(SkillId.MANA_UP, AnimationType.SELF)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            StatusFactory.Apply(source, Status.ManaUp);
        }
    }
    public class MimicSkill : ISkill
    {
        public MimicSkill()
            : base(SkillId.MIMIC, AnimationType.RANGED)
        { Add(Elements.DARK); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { }
    }
    public class MutinySkill : ISkill
    {
        public MutinySkill()
            : base(SkillId.MUTINY, AnimationType.RANGED)
        { Add(Elements.MENTAL, Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target)
        {
            StatusFactory.Apply(target, Status.Mutiny);
        }
    }
    public class RegenAllSkill : ISkill
    {
        public RegenAllSkill()
            : base(SkillId.REGEN_ALL, AnimationType.SELF)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source) 
        {
            base.Activate(source);
            ApplyToPlayers(Status.Regen);
        }
    }
    public class RemoteMineSkill : ISkill
    {
        private static Dictionary<ICreature, RemoteMineSkill> _cache = new Dictionary<ICreature, RemoteMineSkill>();
        public RemoteMineSkill() : base(SkillId.REMOTE_MINE, AnimationType.STATIONARY) { AddCost(StatType.MANA, 10); Add(Elements.FIRE); }
        public override void Activate(ICreature source)
        {
            
            if (!_cache.ContainsKey(source))
            {
                _cache.Add(source, this);
                base.Activate(source);
            }
            else
            {
                if (_cache[source].IsActive())
                {
                    _cache[source].Explode(source);
                    _cache[source].Cleanup(source, null);
                    _cache.Remove(source);
                    Cleanup(source, null);
                }
                else
                {
                    _cache.Remove(source);
                    _cache.Add(source, this);
                    base.Activate(source);
                }
            }
        }
        private void Explode(ICreature source)
        {
            if (_behavior.GetGraphic() != null)
            {
                CreatureFactory.CreateMinion(SkillId.EXPLODE, _source, _behavior.GetGraphic(), _behavior.GetGraphic().GetLocation());
            }
        }
    }
    public class SoulCrushSkill : ISkill
    {
        private Random _rand = new Random();
        public SoulCrushSkill()
            : base(SkillId.SOUL_CRUSH, AnimationType.RANGED)
        { Add(Elements.MENTAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        {
            target.ApplyDamage(5,_source,true,StatType.Values[_rand.Next(0,3)]);
        }
    }
    public class SoulReinforcementSkill : ISkill
    {
        public SoulReinforcementSkill()
            : base(SkillId.SOUL_REINFORCEMENT, AnimationType.SELF)
        { Add(Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void  Activate(ICreature source)
        {
 	        base.Activate(source);
            StatusFactory.Apply(source, Status.Berserk);
        }
    }
    public class SpawnItemSkill : ISkill
    {
        public SpawnItemSkill()
            : base(SkillId.SPAWN_ITEM, AnimationType.SELF)
        { Add(Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            ItemFactory.CreateRandomPlain(source.GetLocation());
        }
    }
    public class SpeedUpSkill : ISkill
    {
        public SpeedUpSkill()
            : base(SkillId.SPEED_UP, AnimationType.SELF)
        { Add(Elements.WATER); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source) 
        {
            base.Activate(source);
            ApplyToPlayers(Status.SpeedUp);
        }
    }
    public class StealItemSkill : ISkill
    {
        private Random _rand = new Random();
        public StealItemSkill()
            : base(SkillId.STEAL_ITEM, AnimationType.ROTATE)
        { Add(Elements.WATER, Elements.AIR); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        {
            if (_rand.Next(100) > 0)
            {
                _source.PickupItem(ItemFactory.CreateRandomPlain());
            }
        }
    }
    public class StrengthUpSkill : ISkill
    {
        public StrengthUpSkill()
            : base(SkillId.STRENGTH_UP, AnimationType.SELF)
        { Add(Elements.FIRE); AddCost(StatType.MANA, 10); }
        public override void Activate(ICreature source)
        {
            base.Activate(source);
            StatusFactory.Apply(source, Status.StrengthUp);
        }
    }
    public class ThrowItemSkill : ISkill
    {
        private float _itemStrength = 0;
        public ThrowItemSkill()
            : base(SkillId.THROW_ITEM, AnimationType.RANGED)
        { Add(Elements.AIR); AddCost(StatType.MANA, 0); }
        public override void  Activate(ICreature source)
        {
            var item = source.DestroyRandomItemFromInventory();
            if (item != null)
            {
                _itemStrength = item.Modifers.GetSum()*3;
                base.Activate(source);
            }
        }
        public override void Affect(ICreature target)
        {
            target.ApplyDamage(_itemStrength, _source);
        }
    }
    public class ValedictorianSkill : ISkill
    {
        public ValedictorianSkill()
            : base(SkillId.VALEDICTORIAN, AnimationType.RANGED)
        { Add(Elements.MENTAL, Elements.LIGHT); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { }
    }
    public class VaporCloudSkill : ISkill
    {
        public VaporCloudSkill()
            : base(SkillId.VAPOR_CLOUD, AnimationType.CLOUD)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) { target.AddBuff(new StatBuff(StatType.MOVE_COOL_DOWN, -10)); }
    }
    public class VaporImplantSkill : ISkill
    {
        public VaporImplantSkill() : base(SkillId.VAPOR_IMPLANT, AnimationType.RANGED) { AddCost(StatType.MANA, 10); Add(Elements.PHYSICAL, Elements.AIR); }
        public override void  Affect(IEntity target)
        {
            CreatureFactory.CreateMinion(SkillId.VAPOR_CLOUD, _source,null,target.GetLocation());
        }
    }
    public class VenomFistSkill : ISkill
    {
        public VenomFistSkill()
            : base(SkillId.VENO_FIST, AnimationType.SELF)
        { Add(Elements.DARK); AddCost(StatType.MANA, 10); }
        public override void  Activate(ICreature source)
        {
 	         base.Activate(source);
             StatusFactory.Apply(source, Status.VenomFist);
        }
    }
    public class WallPunchSkill : ISkill
    {
        public WallPunchSkill()
            : base(SkillId.WALL_PUNCH, AnimationType.RANGED)
        { Add(Elements.EARTH); AddCost(StatType.MANA, 10); }
        public override void  Affect(IEntity target)
        {
            if (target.GetEntityType() == OGUR.EntityType.WALL)
            {
                if (target.GetLocation().GridX > 0 && target.GetLocation().GridX < Dungeons.DungeonFactory.BlocksWide-1 &&
                    target.GetLocation().GridY > 0 && target.GetLocation().GridY < Dungeons.DungeonFactory.BlocksHigh-1)
                {
                    target.SetInactive();
                }
            }
        }
    }
    public class WeakKneesSkill : ISkill
    {
        public WeakKneesSkill()
            : base(SkillId.WEAK_KNEEES, AnimationType.RANGED)
        { Add(Elements.PHYSICAL); AddCost(StatType.MANA, 10); }
        public override void Affect(ICreature target) 
        {
            StatusFactory.Apply(target, Status.WeakKnees);
        }
    }
}