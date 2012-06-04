using OGUR.Creatures;
using OGUR.Entities;
using SPX.Core;
using System;

namespace OGUR.Skills
{
    [Serializable]
    class SkillAnimation
    {
        protected SkillAnimation(){}
        public virtual void Animate(SkillEffect skill,ICreature source,Point2 velocity){}
    }
    [Serializable]
    class NoAnimation : SkillAnimation { }

    [Serializable]
    class SelfAnimation : SkillAnimation
    {
        public override void Animate(SkillEffect skill,ICreature source,Point2 velocity)
        {
            skill.UpdateLocation(source.GetLocation());
        }
    }

    [Serializable]
    class RangedAnimation : SkillAnimation
    {
        public override void Animate(SkillEffect skill, ICreature source, Point2 velocity)
        {
            if (!skill.Move(velocity.X, velocity.Y))
            {
                skill.Cleanup(skill);
            }
        }
    }

    [Serializable]
    class RotateAnimation : SkillAnimation
    {
        private Point2 rotation;
        private Point2 location = new Point2(0, 0);
        public override void Animate(SkillEffect skill, ICreature source, Point2 velocity)
        {
            if (rotation==null)
            {
                rotation = new Point2(source.GetSkillVector().GridX, source.GetSkillVector().GridY);
            }
            location.SetX(rotation.GridX + source.GetLocation().GridX);
            location.SetY(rotation.GridY + source.GetLocation().GridY);
            skill.SetLocation(location);
            rotation.Copy(rotation.RotateClockwise());
        }
    }
}
