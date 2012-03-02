using OGUR.Creatures;
using OGUR.Skills;
using SPX.Core;
using SPX.Entities;

namespace OGUR.Entities
{
    public class SkillEffect:Entity
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
        private readonly Point2 m_direction = new Point2(0, 0);

        public SkillEffect(Point2 gridLocation,Point2 velocity,ICreature source,ISkill skill)
        {
            m_skill = skill;
            Initialize(gridLocation, m_skill.GetSpriteType(), OGUR.EntityType.SKILL_EFFECT,.7f);
            m_velocity.Copy(velocity);
            m_direction.Copy(velocity);
            m_source = source;
            m_startingStrength = m_currentStrength = m_skill.GetStrength();
            m_animation = SkillFactory.Create(m_skill.GetAnimationType());
            m_graphic.SetColor(skill.GetElementColor());
        }

        private IEntity hitTarget;

        public void Cleanup(Entity target)
        {
            m_isActive = false;
            m_skill.Cleanup(target,this);
        }

        public Point2 GetDirection()
        {
            return m_direction;
        }

        public override void Update()
        {
            foreach (var targetType in m_skill.GetTargetTypes())
            {
                hitTarget = EntityManager.GetEntities(targetType, this.GetLocation())[0];
                if (null != hitTarget && hitTarget != this)
                {
                    m_skill.Affect(hitTarget);
                    Cleanup(this);
                }
            }
            if(m_currentStrength<.001)
            {
                Cleanup(m_source);
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
