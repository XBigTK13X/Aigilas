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
        
        private readonly Point2 m_velocity = new Point2(0,0);
        private readonly ICreature m_source;
        private readonly ISkill m_skill;
        private float m_currentStrength = 0;
        private float m_startingStrength = 0;
        private SkillAnimation m_animation;
        private float m_coolDown = CoolDown;
        private const float CoolDown = Stats.DefaultCoolDown/8;

        public SkillEffect(Point2 gridLocation,Point2 velocity,ICreature source,ISkill skill)
        {
            m_skill = skill;
            Initialize(gridLocation, m_skill.GetSpriteType(), GameObjectType.SKILL_EFFECT,.7f);
            m_velocity.Copy(velocity);
            m_source = source;
            m_startingStrength = m_currentStrength = m_skill.GetStrength();
            m_animation = SkillFactory.Create(m_skill.GetAnimationType());
            m_graphic.SetColor(skill.GetElementColor());
        }

        private GameplayObject hitTarget;
        public override void Update()
        {
            foreach (var targetType in m_skill.GetTargetTypes())
            {
                hitTarget = GameplayObjectManager.GetObjects(targetType, this.GetLocation()).FirstOrDefault();
                if (null != hitTarget && hitTarget != this)
                {
                    m_skill.Cleanup(this);
                }
            }
            if(m_currentStrength<.001)
            {
                m_skill.Cleanup(m_source);
                m_isActive = false;
            }
            else
            {
                m_coolDown--;
                if (m_coolDown <= 0)
                {
                    if (m_startingStrength == 0)
                    {
                        m_startingStrength = m_currentStrength;
                    }
                    m_currentStrength *= m_strengthDecayAmount;
                    m_velocity.SetX(m_velocity.X * m_currentStrength);
                    m_velocity.SetY(m_velocity.Y * m_currentStrength);
                    m_animation.Animate(this, m_source, m_velocity);
                    m_isActive = m_skill.AffectTarget(m_source, this);
                    m_coolDown = CoolDown;
                }
            }
        }
    }
}
