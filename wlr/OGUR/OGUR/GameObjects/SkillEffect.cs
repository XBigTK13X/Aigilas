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


        public SkillEffect(float gridX, float gridY,Point2 velocity,ICreature source,ISkill skill,SpriteType sprite=SpriteType.EMPTY,float strength=DefaultStrength)
        {
            m_spriteType = sprite;
            Initialize((int)gridX, (int)gridY, SpriteType.SKILL_EFFECT, GameObjectType.SKILL_EFFECT);
            m_strength = strength;
            m_velocity = velocity;
            m_source = source;
            m_skill = skill;
        }

        public override void Update()
        {         
            if(m_strength<.01)
            {
                m_isActive = false;
            }
            else
            {
                var collidedTarget = m_source.GetTargets().GetCollidedTarget(this);
                if(null!=collidedTarget)
                {
                    m_skill.Affect(collidedTarget);
                    m_isActive = false;
                    return;
                }
                m_strength *= m_strengthDecayAmount;
                m_velocity.X *= m_strength;
                m_velocity.Y *= m_strength;
                Move(m_velocity.X,m_velocity.Y);
            }
        }
    }
}
