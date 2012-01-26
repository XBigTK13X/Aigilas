using System;
using System.Collections.Generic;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Sprites;
using Microsoft.Xna.Framework;
namespace OGUR.Skills
{
    public class AnimationType
    {
        public const int NONE = 0;
        public const int RANGED = 1;
        public const int CLOUD = 2;
        public const int SELF = 3;
        public const int STATIONARY = 4;
        public const int ROTATE = 5;
    }
    public abstract class ISkill
    {
        
        protected ICreature m_source;       
        protected string m_implementationId;        
        protected SkillBehavior m_behavior;
        protected SkillComponents m_components;
        public bool StartOffCenter = false;

        protected ISkill(string implementationId, int animation, float strength = SkillEffect.DefaultStrength,bool isPersistent=false,int effectGraphic = SpriteType.SKILL_EFFECT)
        {
            m_implementationId = implementationId;
            m_components = new SkillComponents(strength, isPersistent);
            m_behavior = SkillFactory.Create(animation,effectGraphic,this);
        }

        protected void Add(params int[] elements){m_components.AddElements(elements);}
        protected void AddCost(string stat, float cost){m_behavior.AddCost(stat, cost);}
        public virtual void Buff(ICreature target){m_components.Buff(target);}
        public void SetBuff(string stat, float amount) { m_components.SetBuff(stat, amount); }

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
        public List<int> GetElements() { return m_components.GetElements(); }
        public virtual void Affect(ICreature target) { }
        public int GetSpriteType(){return m_behavior.GetSpriteType();}
        public int GetAnimationType() { return m_behavior.GetAnimationType(); }
        public float GetStrength(){return m_components.GetStrength();}
        public bool IsPersistent() { return m_components.IsPersistent(); }
        public Color GetElementColor() { return Elements.Colors[m_components.GetElements()[0]];}
        public virtual void Cleanup(GameplayObject target,SkillEffect source){m_behavior.Cleanup(target);}
        public bool AffectTarget(ICreature target, SkillEffect graphic){return m_behavior.AffectTarget(target, graphic);}
        public List<int> GetTargetTypes()
        {
            return m_components.GetTargetTypes();
        }
    }
}
