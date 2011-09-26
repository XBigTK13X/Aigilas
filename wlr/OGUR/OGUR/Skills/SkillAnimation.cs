using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Creatures;
using OGUR.Collision;

namespace OGUR.Skills
{
    class SkillAnimation
    {
        protected SkillAnimation(){}
        public virtual void Animate(SkillEffect skill,ICreature source,Point2 velocity){}
    }
    class NoAnimation : SkillAnimation { }

    class SelfAnimation : SkillAnimation
    {
        public override void Animate(SkillEffect skill,ICreature source,Point2 velocity)
        {
            skill.UpdateLocation(source.GetLocation());
        }
    }

    class RangedAnimation : SkillAnimation
    {
        public override void Animate(SkillEffect skill, ICreature source, Point2 velocity)
        {
            skill.Move(velocity.X, velocity.Y);
        }
    }

    
}
