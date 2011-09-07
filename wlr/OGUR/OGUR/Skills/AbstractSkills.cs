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
        protected Stats m_cost;
        protected ICreature m_source;
        protected List<Elements> m_elements = new List<Elements>() {Elements.NORMAL};
        public static ISkill NULL;
        protected string m_implementationId;
        protected List<SkillEffect> m_effectGraphics = new List<SkillEffect>();
        protected Skill.Animation m_animation;
        protected SpriteType m_effectSprite = SpriteType.SKILL_EFFECT;
        protected StatBuff m_buff;
        protected float m_effectStrength = 0;
        protected bool m_isPersistent = false;

        protected ISkill(string implementationId, Skill.Animation animation, float strength = SkillEffect.DefaultStrength,bool isPersistent=false,SpriteType effectGraphic = SpriteType.SKILL_EFFECT)
        {
            m_elements = new List<Elements>() { Elements.NORMAL };
            m_cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            m_implementationId = implementationId;
            m_effectSprite = effectGraphic;
            m_animation = animation;
            m_effectStrength = strength;
            m_isPersistent = isPersistent;
        }

        protected void Add(params Elements[] elements)
        {
            m_elements.AddRange(elements);
        }

        protected void AddCost(StatType stat, float cost)
        {
            
           m_cost.AddBuff(new StatBuff(stat,cost));
        }

        public override string ToString()
        {
            return m_implementationId;
        }

        public virtual void Activate(ICreature source)
        {
            m_source = source;
            switch(m_animation)
            {
                case Skill.Animation.RANGED:
                    UseInDirectionFaced();
                    break;
                case Skill.Animation.SELF:
                    UseOnSelf();
                    break;
                case Skill.Animation.CLOUD:
                    UseInSurroundingArea();
                    break;
                case Skill.Animation.STATIONARY:
                    UseInCurrentLocation();
                    break;
            }
            
        }
        public virtual void Affect(GameplayObject target)
        {
            var creature = IsCreature(target);
            if (creature != null)
            {
                Affect(creature);
            }
        }
        public abstract void Affect(ICreature target);
        public virtual void Buff(ICreature target)
        {
            target.AddBuff(m_buff);
        }

        protected void UseInDirectionFaced()
        {
            AddGraphic(m_source.GetLocation(), m_source.GetSkillVector());
        }

        protected void UseOnSelf()
        {
            AddGraphic(m_source.GetLocation(), new Point2(0, 0));
        }

        protected void UseInSurroundingArea()
        {
            var referencePoint = m_source.GetLocation();
            for(var ii = -1;ii<2;ii++)
            {
                for(var jj =-1;jj<2;jj++)
                {
                    if(ii!=0||jj!=0)
                    {
                        var cloudPosition = new Point2(referencePoint.GridX + ii, referencePoint.GridY + jj);
                        AddGraphic(cloudPosition,new Point2(0,0));
                    }
                }
            }
        }

        protected void UseInCurrentLocation()
        {
            AddGraphic(m_source.GetLocation(), new Point2(0, 0));
        }

        protected void AddGraphic(Point2 gridLocation,Point2 velocity)
        {
            var effect = new SkillEffect(gridLocation, velocity, m_source, this, m_animation, m_effectSprite,m_effectStrength,m_isPersistent);
            m_effectGraphics.Add(effect);
            GameplayObjectManager.AddObject(effect);
        }

        public ICreature IsCreature(GameplayObject target)
        {
            if(target.GetObjectType()==GameObjectType.CREATURE)
            {
                return (ICreature) target;
            }
            return null;
        }
    }
}
