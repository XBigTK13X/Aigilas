using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Sprites;
using OGUR.Creatures;
using OGUR.Collision;

namespace OGUR.Skills
{
    public class SkillBehavior
    {
        protected SideEffects m_sideEffects;
        protected ISkill m_parent;
        public SkillBehavior(SpriteType effectGraphic, Skill.Animation animation,ISkill parentSkill)
        {
            m_parent = parentSkill;
            m_sideEffects = new SideEffects(effectGraphic, animation,m_parent);
        }
        public SpriteType GetSpriteType() { return m_sideEffects.GetSpriteType(); }
        public virtual void Activate(ICreature target) { }

        internal Skill.Animation GetAnimationType()
        {
            return m_sideEffects.GetAnimationType();
        }
    }
    public class RangedBehavior: SkillBehavior
    {
        public RangedBehavior(SpriteType effectGraphic,ISkill parentSkill):base(effectGraphic,Skill.Animation.RANGED,parentSkill){}
        public override void Activate(ICreature target){m_sideEffects.Generate(target.GetLocation(),target.GetSkillVector(),target);}
    }
    public class SelfBehavior:SkillBehavior
    {
        public SelfBehavior(SpriteType effectGraphic, ISkill parentSkill) : base(effectGraphic, Skill.Animation.SELF,parentSkill) { }
        public override void Activate(ICreature target){m_sideEffects.Generate(target.GetLocation(),new Point2(0,0),target);}
    }
    public class StationaryBehavior : SkillBehavior
    {
        public StationaryBehavior(SpriteType effectGraphic, ISkill parentSkill) : base(effectGraphic, Skill.Animation.STATIONARY,parentSkill) { }
        public override void Activate(ICreature target) { m_sideEffects.Generate(target.GetLocation(), new Point2(0, 0), target); }
    }
    public class CloudBehavior:SkillBehavior
    {
        public CloudBehavior(SpriteType effectGraphic, ISkill parentSkill) : base(effectGraphic, Skill.Animation.CLOUD,parentSkill) { }
        public override void Activate(ICreature target)
        {
            var referencePoint = target.GetLocation();
            for(var ii = -1;ii<2;ii++)
            {
                for(var jj =-1;jj<2;jj++)
                {
                    if(ii!=0||jj!=0)
                    {
                        var cloudPosition = new Point2(referencePoint.GridX + ii, referencePoint.GridY + jj);
                        m_sideEffects.Generate(cloudPosition,new Point2(0,0),target);
                    }
                }
            }
        }
    }
}
