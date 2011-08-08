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
            SELF
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

        protected ISkill(string implementationId, Skill.Animation animation, Stats cost = null, 
                         IEnumerable<Elements> elements = null, SpriteType effectGraphic = SpriteType.SKILL_EFFECT)
        {
            m_elements = elements==null ? new List<Elements>() { Elements.NORMAL } : new List<Elements>(elements);
            m_cost = cost==null ? new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) : new Stats(cost);
            m_implementationId = implementationId;
            m_effectSprite = effectGraphic;
            m_animation = animation;
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
            var effect = new SkillEffect(m_source.GetLocation(), m_source.GetSkillVector(), m_source, this, m_animation, m_effectSprite);
            GameplayObjectManager.AddObject(effect);
            m_effectGraphics.Add(effect);
        }

        protected void UseOnSelf()
        {
            var effect = new SkillEffect(m_source.GetLocation(), new Point2(0, 0), m_source, this, m_animation,m_effectSprite);
            GameplayObjectManager.AddObject(effect);
            m_effectGraphics.Add(effect);
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
                        var effect = new SkillEffect(cloudPosition, new Point2(0, 0), m_source, this, m_animation, m_effectSprite);
                        m_effectGraphics.Add(effect);
                        GameplayObjectManager.AddObject(effect);
                    }
                }
            }
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
