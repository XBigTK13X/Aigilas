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
    public class SkillId
    {
        public const string BULK = "Bulk";
        public const string FIREBALL = "Fireball";
        public const string NO_SKILL = "No Skill";
        public const string THRASH = "Thrash";
    }
    public class NoSkill:ISkill
    {
        public NoSkill():base(SkillId.NO_SKILL,Skill.Animation.NONE){}
        public override void Activate(ICreature source){}
        public override void Affect(GameplayObject target){}
        public override void Affect(ICreature target){}
    }
    public class FireballSkill:ISkill
    {
        public FireballSkill() : base(SkillId.FIREBALL,Skill.Animation.RANGED,new Stats(0,5,0,0,0,0,0,0,0,0,0),new List<Elements>(){Elements.FIRE}){}

        public override void Affect(ICreature target)
        {
            target.ApplyDamage(20,target);
        }
    }
    public class ThrashSkill : ISkill
    {
        public ThrashSkill() : base(SkillId.THRASH, Skill.Animation.CLOUD) { }

        public override void Affect(ICreature target)
        {
            target.ApplyDamage(30,target);
        }
    }

    public class BulkSkill : ISkill
    {
        public BulkSkill()
            : base(SkillId.BULK, Skill.Animation.SELF)
        {
            m_buff = new StatBuff(StatType.STRENGTH, 20);
        }

        public override void Affect(ICreature target)
        {
            Buff(target);
        }
    }
}