using System;
using System.Collections.Generic;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Sprites;
namespace OGUR.Skills
{
    public class Skill
    {
        public enum Animation
        {
            NONE,
            RANGED,
            CLOUD,
            SELF,
            STATIONARY
        }
    }
    public abstract class ISkill
    {
        
        protected ICreature m_source;       
        protected string m_implementationId;        
        protected SkillBehavior m_behavior;
        protected SkillComponents m_components;

        protected ISkill(string implementationId, Skill.Animation animation, float strength = SkillEffect.DefaultStrength,bool isPersistent=false,SpriteType effectGraphic = SpriteType.SKILL_EFFECT)
        {
            m_implementationId = implementationId;
            m_components = new SkillComponents(strength, isPersistent);
            m_behavior = SkillFactory.Create(animation,effectGraphic,this,m_components);
        }

        protected void Add(params Elements[] elements){m_components.AddElements(elements);}
        protected void AddCost(StatType stat, float cost){m_components.AddCost(stat, cost);}
        public virtual void Buff(ICreature target){m_components.Buff(target);}
        public void SetBuff(StatType stat, float amount) { m_components.SetBuff(stat, amount); }

        public virtual void Activate(ICreature source)
        {
            m_source = source;
            m_behavior.Activate(source);
            
        }
        public virtual void Affect(GameplayObject target)
        {
            var creature = target.IsCreature();
            if (creature != null)
            {
                Affect(creature);
            }
        }
        public abstract void Affect(ICreature target);

        public override string ToString()
        {
            return m_implementationId;
        }

        public SpriteType GetSpriteType()
        {
            return m_behavior.GetSpriteType();
        }

        public Skill.Animation GetAnimationType()
        {
            return m_behavior.GetAnimationType();
        }

        internal float GetStrength()
        {
            return m_components.GetStrength();
        }

        internal bool IsPersistent()
        {
            return m_components.IsPersistent();
        }
    }
}
