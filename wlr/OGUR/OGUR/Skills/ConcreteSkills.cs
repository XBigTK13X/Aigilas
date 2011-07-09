using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Skills
{
    public class NoSkill:ISkill
    {
        public NoSkill():base(SkillId.NO_SKILL)
        {
            
        }
        public override void Activate(ICreature source)
        {}

        protected override void Affect(ICreature target)
        {}
    }
    public class FireballSkill:ISkill
    {
        public FireballSkill() : base(SkillId.FIREBALL)
        {
        }

        public override void Activate(ICreature source)
        {
            throw new NotImplementedException();
        }

        protected override void Affect(ICreature target)
        {
            throw new NotImplementedException();
        }
    }
}