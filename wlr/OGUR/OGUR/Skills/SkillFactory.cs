using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Sprites;
using OGUR.GameObjects;

namespace OGUR.Skills
{
    public class SkillId
    {
        public const string BULK = "Bulk";
        public const string FIREBALL = "Fireball";
        public const string NO_SKILL = "No Skill";
        public const string THRASH = "Thrash";
        //Sloth
        public const string FLOOR_SPIKES = "Floor Spikes";
        public const string DART = "Dart";
        public const string ACID_DRIP = "Acid Drip";
        public const string ACID_NOZZLE = "Acid Nozzle";
        public const string REMOTE_MINE = "Remote Mine";
        public const string VAPOR_IMPLANT = "Vapor Implant";
        //Envy
        public const string CONFUSION = "Confusion";
        public const string WEAK_KNEEES = "Weak Knees";
        public const string VENOM_FIST = "Venom Fist";
        public const string ABSORB = "Absorb";
        public const string MUTINY = "Mutiny";
        //Greed
        public const string SOUL_REINFORCEMENT = "Soul Reinforcement";
        public const string HORDER = "Horder";
        public const string SPAWN_ITEM = "Drop Rate (+)";
        public const string THROW_ITEM = "Throw Item";
        public const string STEAL_ITEM = "Steal Item";
        //Wrath
        public const string FLAME_HAMMER = "Flame Hammer";
        public const string GUSH = "Gush";
        public const string SOUL_CRUSH = "Soul Crush";
        public const string COMBUST = "Combust";
        public const string HORRIFY = "Horrify";
        //Gluttony
        public const string FORGET_SKILL = "Forget Skill";
        //Lust
        public const string REGEN_ALL = "Regen All";
        public const string SPEED_UP = "Speed (+) All";
        public const string ENVENOM = "Envenom";
        public const string MAGIC_MAP = "Magic Map";
        public const string COLD_SHOULDER = "Cold Shoulder";
        public const string CAVALRY = "Cavalry";
        //Pride
        public const string STRENGTH_UP = "Strength (+)";
        public const string MANA_UP = "Mana (+)";
        public const string ELECTRIFY = "Electrify";
        public const string WALL_PUNCH = "Wall Punch";
        public const string MIMIC = "Mimic";
        public const string VALEDICTORIAN = "Valedictorian";

        public static readonly List<string> Values = new List<string>()
        {
            SkillId.ABSORB,
            SkillId.ACID_DRIP,
            SkillId.ACID_NOZZLE,
            SkillId.CAVALRY,
            SkillId.COLD_SHOULDER,
            SkillId.COMBUST,
            SkillId.CONFUSION,
            SkillId.DART,
            SkillId.ELECTRIFY,
            SkillId.ENVENOM,
            SkillId.FIREBALL,
            SkillId.FLAME_HAMMER,
            SkillId.FLOOR_SPIKES,
            SkillId.FORGET_SKILL,
            SkillId.GUSH,
            SkillId.HORDER,
            SkillId.HORRIFY,
            SkillId.MAGIC_MAP,
            SkillId.MANA_UP,
            SkillId.MIMIC,
            SkillId.MUTINY,
            SkillId.NO_SKILL,
            SkillId.REGEN_ALL,
            SkillId.REMOTE_MINE,
            SkillId.SOUL_CRUSH,
            SkillId.SOUL_REINFORCEMENT,
            SkillId.SPAWN_ITEM,
            SkillId.SPEED_UP,
            SkillId.STEAL_ITEM,
            SkillId.STRENGTH_UP,
            SkillId.THROW_ITEM,
            SkillId.VALEDICTORIAN,
            SkillId.VAPOR_IMPLANT,
            SkillId.VENOM_FIST,
            SkillId.WALL_PUNCH,
            SkillId.WEAK_KNEEES
        };
    }
    class SkillFactory
    {
        public static ISkill Create(string idSkill)
        {
            switch(idSkill)
            {
                case SkillId.ABSORB:return new AbsorbSkill();
                case SkillId.ACID_DRIP:return new AcidDripSkill();
                case SkillId.ACID_NOZZLE:return new AcidNozzleSkill();
                case SkillId.CAVALRY:return new CalvarySkill();
                case SkillId.COLD_SHOULDER: return new ColdShoulderSkill();
                case SkillId.COMBUST: return new CombustSkill();
                case SkillId.CONFUSION:return new ConfusionSkill();
                case SkillId.DART:return new DartSkill();
                case SkillId.ELECTRIFY: return new ElectrifySkill();
                case SkillId.ENVENOM: return new EnvenomSkill();
                case SkillId.FIREBALL:return new FireballSkill();
                case SkillId.FLAME_HAMMER: return new FlameHammerSkill();
                case SkillId.FLOOR_SPIKES:return new FloorSpikesSkill();
                case SkillId.FORGET_SKILL: return new ForgetSkill();
                case SkillId.GUSH: return new GushSkill();
                case SkillId.HORDER: return new HorderSkill();
                case SkillId.HORRIFY: return new HorrifySkill();
                case SkillId.MAGIC_MAP: return new MagicMapSkill();
                case SkillId.MANA_UP: return new ManaUpSkill();
                case SkillId.MIMIC: return new MimicSkill();
                case SkillId.MUTINY: return new MutinySkill();
                case SkillId.NO_SKILL:return new NoSkill();
                case SkillId.REGEN_ALL: return new RegenAllSkill();
                case SkillId.REMOTE_MINE:return new RemoteMineSkill();
                case SkillId.SOUL_CRUSH: return new SoulCrushSkill();
                case SkillId.SOUL_REINFORCEMENT: return new SoulReinforcementSkill();
                case SkillId.SPAWN_ITEM: return new SpawnItemSkill();
                case SkillId.SPEED_UP: return new SpeedUpSkill();
                case SkillId.STEAL_ITEM: return new StealItemSkill();
                case SkillId.STRENGTH_UP: return new StrengthUpSkill();
                case SkillId.THROW_ITEM: return new ThrowItemSkill();
                case SkillId.VALEDICTORIAN: return new ValedictorianSkill();
                case SkillId.VAPOR_IMPLANT:return new VaporImplantSkill();
                case SkillId.VENOM_FIST: return new VenomFistSkill();
                case SkillId.WALL_PUNCH:return new WallPunchSkill();
                case SkillId.WEAK_KNEEES:return new WeakKneesSkill();
                default:
                    throw new Exception("You forgot to define the new skill in the Factory...YOU FOOL!"); 
            }
        }

        private static Dictionary<int, List<string>> s_elementMap = new Dictionary<int, List<string>>();
        private static Dictionary<string, int> s_skillAnimationMap = new Dictionary<string,int>();

        private static void GenerateStaticSkillMaps()
        {
            if (s_elementMap.Count() == 0)
            {
                ISkill skill;
                foreach (var skillId in SkillId.Values)
                {
                    if (skillId != SkillId.NO_SKILL)
                    {
                        skill = Create(skillId);
                        foreach (var elem in skill.GetElements())
                        {
                            if (!s_elementMap.ContainsKey(elem))
                            {
                                s_elementMap.Add(elem, new List<string>());
                            }
                            s_elementMap[elem].Add(skillId);
                        }
                        s_skillAnimationMap[skillId] = skill.GetAnimationType();
                    }
                }
            }
        }

        private static Random rand = new Random();
        public static string GetElementalSkill(int elementId)
        {
            GenerateStaticSkillMaps();
            return s_elementMap[elementId][rand.Next(0, s_elementMap[elementId].Count())];
        }

        public static bool IsSkill(string skillId,int animationType)
        {
            return s_skillAnimationMap[skillId] == animationType;
        }

        public static SkillBehavior Create(int animation,int skillGraphic,ISkill parentSkill)
        {
            switch (animation)
            {
                case AnimationType.CLOUD:
                    return new CloudBehavior(skillGraphic, parentSkill);
                case AnimationType.RANGED:
                    return new RangedBehavior(skillGraphic, parentSkill);
                case AnimationType.SELF:
                    return new SelfBehavior(skillGraphic, parentSkill);
                case AnimationType.STATIONARY:
                    return new StationaryBehavior(skillGraphic, parentSkill);
                default:
                    throw new Exception("How dare you create a new Anim type for skills without defining a proper behavior for them!");
            }
        }

        public static SkillAnimation Create(int animation)
        {
            switch (animation)
            {
                case AnimationType.RANGED:
                    return new RangedAnimation();
                case AnimationType.SELF:
                    return new SelfAnimation();
                default:
                    return new NoAnimation();
            }
        }
    }
}
