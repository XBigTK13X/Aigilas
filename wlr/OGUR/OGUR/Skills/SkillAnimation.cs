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

    class RotateAnimation : SkillAnimation
    {
        private Point2 rotation = new Point2(-1,-1);
        private Point2 location = new Point2(0, 0);
        public override void Animate(SkillEffect skill, ICreature source, Point2 velocity)
        {
            if (rotation.X == -1)
            {
                rotation.Copy(source.GetSkillVector());
            }
            location.SetX(rotation.GridX + source.GetLocation().GridX);
            location.SetY(rotation.GridY + source.GetLocation().GridY);
            skill.SetLocation(location);
            Console.WriteLine(rotation.GridX + " : " + rotation.GridY);
            rotation.Copy(rotation.RotateClockwise());
        }
    }
}
