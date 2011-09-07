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
        
        private float m_strength;
        private readonly Point2 m_velocity;
        private readonly ICreature m_source;
        private readonly ISkill m_skill;
        private SpriteType m_spriteType;
        private readonly Skill.Animation m_animation;
        private float m_startingStrength;
        private bool m_used = false;
        private bool m_isPersistent = false;

        public SkillEffect(Point2 gridLocation,Point2 velocity,ICreature source,ISkill skill,
                           Skill.Animation animation,SpriteType sprite=SpriteType.SKILL_EFFECT,float strength=DefaultStrength,bool isPersistent = false)
        {
            m_spriteType = sprite;
            Initialize(gridLocation, sprite, GameObjectType.SKILL_EFFECT);
            m_strength = strength;
            m_velocity = velocity;
            m_source = source;
            m_skill = skill;
            m_animation = animation;
            m_isPersistent = isPersistent;
        }

        public override void Update()
        {         
            if(m_strength<.001)
            {
                if (m_animation == Skill.Animation.SELF && m_used)
                {
                    m_skill.Affect(m_source);
                }
                m_isActive = false;
            }
            else
            {
                if (m_startingStrength == 0)
                {
                    m_startingStrength = m_strength;
                }
                m_strength *= m_strengthDecayAmount;
                m_velocity.SetX(m_velocity.X*m_strength);
                m_velocity.SetY(m_velocity.Y*m_strength);
                switch(m_animation)
                {
                    case Skill.Animation.CLOUD:
                        CloudAnimation();
                        break;
                    case Skill.Animation.RANGED:
                        RangedAnimation();
                        break;
                    case Skill.Animation.SELF:
                        SelfAnimation();
                        break;
                    case Skill.Animation.STATIONARY:
                        StationaryAnimation();
                        break;
                }
                if(m_animation==Skill.Animation.SELF)
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
                        if (!m_isPersistent)
                        {
                            m_isActive = false;
                        }
                        return;
                    }    
                }
            }
        }

        private void StationaryAnimation()
        {

        }

        private void RangedAnimation()
        {
            Move(m_velocity.X,m_velocity.Y);
        }

        private void CloudAnimation()
        {
            
        }

        private void SelfAnimation()
        {
            SetLocation(m_source.GetLocation());
        }
    }
}
