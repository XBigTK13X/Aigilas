using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Sprites;

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
        public const string DROP_RATE_UP = "Drop Rate (+)";
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
    }
    class SkillFactory
    {
        public static ISkill Create(string skillId)
        {
            switch(skillId)
            {
                case SkillId.ACID_NOZZLE:
                    return new AcidNozzleSkill();
                case SkillId.BULK:
                    return new BulkSkill();
                case SkillId.DART:
                    return new DartSkill();
                case SkillId.FIREBALL:
                    return new FireballSkill();
                case SkillId.FLOOR_SPIKES:
                    return new FloorSpikesSkill();
                case SkillId.NO_SKILL:
                    return new NoSkill();
                case SkillId.REMOTE_MINE:
                    return new RemoteMineSkill();                
                case SkillId.THRASH:
                    return new ThrashSkill();
                case SkillId.VAPOR_IMPLANT:
                    return new VaporImplantSkill();
                default:
                    throw new Exception("You forgot to define the new skill in the Factory...YOU FOOL!"); 
            }
        }

        public static SkillBehavior Create(Skill.Animation animation,SpriteType skillGraphic,ISkill parentSkill,SkillComponents skillComponents)
        {
            switch (animation)
            {
                case Skill.Animation.CLOUD:
                    return new CloudBehavior(skillGraphic, parentSkill);
                case Skill.Animation.RANGED:
                    return new RangedBehavior(skillGraphic, parentSkill);
                case Skill.Animation.SELF:
                    return new SelfBehavior(skillGraphic, parentSkill);
                case Skill.Animation.STATIONARY:
                    return new StationaryBehavior(skillGraphic, parentSkill);
                default:
                    throw new Exception("How dare you create a new Anim type for skills without defining a proper behavvior for them!");
            }
        }
    }
}
