using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Sprites;
using OGUR.Creatures;
using OGUR.Collision;
using OGUR.GameObjects;

namespace OGUR.Skills
{
    public class SkillBehavior
    {
        protected SideEffects m_sideEffects;
        protected ISkill m_parent;
        protected bool m_used = false;
        protected Stats m_cost;

        public SkillBehavior(int effectGraphic, int animation,ISkill parentSkill)
        {
            m_parent = parentSkill;
            m_sideEffects = new SideEffects(effectGraphic, animation,m_parent);
            m_cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
        public int GetSpriteType() { return m_sideEffects.GetSpriteType(); }
        public virtual void Activate(ICreature target) { }
        public virtual void Cleanup(GameplayObject target) { }
        public void AddCost(string stat, float cost)
        {
            m_cost.AddBuff(new StatBuff(stat, cost));
        }
        protected bool SubtractCost(ICreature owner)
        {
            bool costPaid = false;
            foreach (string stat in StatType.Values)
            {
                if (stat != StatType.REGEN)
                {
                    if (owner.LowerStat(stat, m_cost.Get(stat)))
                    {
                        costPaid = true;
                    }
                }
            }
            return costPaid;
        }
        private GameplayObject hitTarget;
        public virtual bool AffectTarget(ICreature source,SkillEffect graphic)
        {
            hitTarget = source.GetTargets().GetCollidedTarget(graphic);
            if (null != hitTarget && hitTarget!=source)
            {
                m_parent.Affect(hitTarget);
                if (!m_parent.IsPersistent())
                {
                    return false;
                }
            }
            return true;
        }
        internal int GetAnimationType()
        {
            return m_sideEffects.GetAnimationType();
        }
    }
    public class RangedBehavior: SkillBehavior
    {
        public RangedBehavior(int effectGraphic, ISkill parentSkill) : base(effectGraphic, AnimationType.RANGED, parentSkill) { }
        public override void Activate(ICreature target) { if (SubtractCost(target)) { m_sideEffects.Generate(target.GetLocation(), target.GetSkillVector(), target); } }
    }
    public class SelfBehavior:SkillBehavior
    {
        public SelfBehavior(int effectGraphic, ISkill parentSkill) : base(effectGraphic, AnimationType.SELF, parentSkill) { }
        public override void Activate(ICreature target) { if (SubtractCost(target)) { m_sideEffects.Generate(target.GetLocation(), new Point2(0, 0), target); } }
        public override bool AffectTarget(ICreature source, SkillEffect graphic)
        {
            if (!m_used)
            {
                source.Combo(m_parent.GetElements());
                m_parent.Affect(source);
                m_used = true;
            }
            return true;
        }
    }
    public class StationaryBehavior : SkillBehavior
    {
        public StationaryBehavior(int effectGraphic, ISkill parentSkill) : base(effectGraphic, AnimationType.STATIONARY, parentSkill) { }
        public override void Activate(ICreature target) 
        {
            if(SubtractCost(target))
            {
                if (m_parent.StartOffCenter)
                {
                    var location = new Point2(target.GetLocation().GridX + target.GetSkillVector().GridX, target.GetLocation().GridY + target.GetSkillVector().GridY);
                    m_sideEffects.Generate(location, new Point2(0,0), target);
                }
                else
                {
                    m_sideEffects.Generate(target.GetLocation(), new Point2(0,0), target);
                }
            }
        }
    }
    public class CloudBehavior:SkillBehavior
    {
        public CloudBehavior(int effectGraphic, ISkill parentSkill) : base(effectGraphic, AnimationType.CLOUD, parentSkill) { }
        public override void Activate(ICreature target)
        {
            if (SubtractCost(target))
            {
                var referencePoint = target.GetLocation();
                for (var ii = -1; ii < 2; ii++)
                {
                    for (var jj = -1; jj < 2; jj++)
                    {
                        if (ii != 0 || jj != 0)
                        {
                            var cloudPosition = new Point2(referencePoint.GridX + ii, referencePoint.GridY + jj);
                            m_sideEffects.Generate(cloudPosition, new Point2(0, 0), target);
                        }
                    }
                }
            }
        }
    }
    public class RotateBehavior : SkillBehavior
    {
        public RotateBehavior(int effectGraphic, ISkill parentSkill) : base(effectGraphic, AnimationType.ROTATE, parentSkill) { }
        public override void Activate(ICreature target)
        {
            m_sideEffects.Generate(target.GetLocation(), new Point2(0, 0), target);
        }
    }
}
