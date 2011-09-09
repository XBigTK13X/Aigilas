using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Skills;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class SkillEffect:GameplayObject
    {
        private const float m_strengthDecayAmount = .75f;
        public const float DefaultStrength = 1;
        
        private readonly Point2 m_velocity;
        private readonly ICreature m_source;
        private readonly ISkill m_skill;
        private bool m_used = false;
        private float m_currentStrength = 0;
        private float m_startingStrength = 0;
        private SkillAnimation m_animation;

        public SkillEffect(Point2 gridLocation,Point2 velocity,ICreature source,ISkill skill)
        {
            m_skill = skill;
            Initialize(gridLocation, m_skill.GetSpriteType(), GameObjectType.SKILL_EFFECT);
            m_velocity = velocity;
            m_source = source;
            m_startingStrength = m_currentStrength = m_skill.GetStrength();
            m_animation = SkillFactory.Create(m_skill.GetAnimationType());
        }

        public override void Update()
        {         
            if(m_currentStrength<.001)
            {
                if (m_animation.GetType()==typeof(SelfAnimation) && m_used)
                {
                    m_skill.Affect(m_source);
                }
                m_isActive = false;
            }
            else
            {
                if (m_startingStrength == 0)
                {
                    m_startingStrength = m_currentStrength;
                }
                m_currentStrength *= m_strengthDecayAmount;
                m_velocity.SetX(m_velocity.X*m_currentStrength);
                m_velocity.SetY(m_velocity.Y*m_currentStrength);
                m_animation.Animate(this,m_source,m_velocity);
                if(m_animation.GetType()==typeof(SelfAnimation))
                {
                    if (!m_used)
                    {
                        m_skill.Affect(m_source);
                        m_used = true;
                        return;
                    }
                }
                else
                {
                    var collidedTarget = m_source.GetTargets().GetCollidedTarget(this);
                    if (null != collidedTarget)
                    {
                        m_skill.Affect(collidedTarget);
                        if (!m_skill.IsPersistent())
                        {
                            m_isActive = false;
                        }
                        return;
                    }    
                }
            }
        }
    }
}
