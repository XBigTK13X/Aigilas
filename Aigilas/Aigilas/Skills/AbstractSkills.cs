using System.Collections.Generic;
using Microsoft.Xna.Framework;
using Aigilas.Creatures;
using Aigilas.Entities;
using Aigilas.Statuses;
using SPX.Entities;
using Aigilas.Management;

namespace Aigilas.Skills
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
        protected ICreature _source;       
        protected string _implementationId;        
        protected SkillBehavior _behavior;
        protected SkillComponents _components;
        public bool StartOffCenter = false;

        protected ISkill(string implementationId, int animation, float strength = SkillEffect.DefaultStrength,bool isPersistent=false,int effectGraphic = SpriteType.SKILL_EFFECT)
        {
            _implementationId = implementationId;
            _components = new SkillComponents(strength, isPersistent);
            _behavior = SkillFactory.Create(animation,effectGraphic,this);
        }

        protected void Add(params int[] elements){_components.AddElements(elements);}
        protected void AddCost(string stat, float cost){_behavior.AddCost(stat, cost);}
        public virtual void Buff(ICreature target){_components.Buff(target);}
        public void SetBuff(string stat, float amount) { _components.SetBuff(stat, amount); }

        public virtual void Activate(ICreature source)
        {
            _source = source;
            _behavior.Activate(source);
        }
        public virtual void Affect(IEntity target)
        {
            var creature = target.IsCreature();
            if (creature != null)
            {
                Affect(creature);
            }
        }
        public void ApplyToPlayers(int statusId)
        {
            foreach (var player in EntityManager.GetPlayers())
            {
                StatusFactory.Apply(player as ICreature, statusId);
            }
        }
        public List<int> GetElements() { return _components.GetElements(); }
        public virtual void Affect(ICreature target) { }
        public int GetSpriteType(){return _behavior.GetSpriteType();}
        public int GetAnimationType() { return _behavior.GetAnimationType(); }
        public float GetStrength(){return _components.GetStrength();}
        public bool IsPersistent() { return _components.IsPersistent(); }
        public Color GetElementColor() { return Elements.Colors[_components.GetElements()[0]];}
        public virtual void Cleanup(Entity target,SkillEffect source){_behavior.Cleanup(target,source);}
        public bool AffectTarget(ICreature target, SkillEffect graphic){return _behavior.AffectTarget(target, graphic);}
        public bool IsActive()
        {
            return _behavior.IsActive();
        }
        public List<int> GetTargetTypes()
        {
            return _components.GetTargetTypes();
        }

        public string GetSkillId()
        {
            return _implementationId;
        }

        public float GetCost()
        {
            return _behavior.GetCost();
        }
    }
}
