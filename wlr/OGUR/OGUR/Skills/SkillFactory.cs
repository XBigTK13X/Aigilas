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
                case SkillId.BULK:
                    return new BulkSkill();
                case SkillId.NO_SKILL:    
                    return new NoSkill();
                default:
                    throw new Exception("You forgot to define the new skill in the Factory...YOU FOOL!"); 
            }
        }
    }
}
