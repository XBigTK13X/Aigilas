using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Skills
{
    class SkillFactory
    {
        public static ISkill Create(string skillId)
        {
            switch(skillId)
            {
                case SkillId.FIREBALL:
                    return new FireballSkill();
                case SkillId.THRASH:
                    return new ThrashSkill();
                default:
                    return new NoSkill();
            }
        }
    }
}
