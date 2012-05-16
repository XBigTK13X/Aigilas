using OGUR.Creatures;
using OGUR.Skills;
using SPX.Core;
using SPX.Entities;
using System;

namespace OGUR.Entities
{
    public class SkillEffect:Entity
    {
        private const float _strengthDecayAmount = .75f;
        public const float DefaultStrength = 1;
        
        private readonly Point2 _velocity = new Point2(0,0);
        private readonly ICreature _source;
        private readonly ISkill _skill;
        private float _currentStrength = 0;
        private float _startingStrength = 0;
        private SkillAnimation _animation;
        private float _coolDown = CoolDown;
        private const float CoolDown = Stats.DefaultCoolDown/8;
        private readonly Point2 _direction = new Point2(0, 0);

        public SkillEffect(Point2 gridLocation,Point2 velocity,ICreature source,ISkill skill)
        {
            _skill = skill;
            Initialize(gridLocation, _skill.GetSpriteType(), OGUR.EntityType.SKILL_EFFECT,.7f);
            _velocity.Copy(velocity);
            _direction.Copy(velocity);
            _source = source;
            _startingStrength = _currentStrength = _skill.GetStrength();
            _animation = SkillFactory.Create(_skill.GetAnimationType());
            _graphic.SetColor(skill.GetElementColor());
        }

        private IEntity hitTarget;

        public void Cleanup(Entity target)
        {
            _isActive = false;
            _skill.Cleanup(target,this);
        }

        public Point2 GetDirection()
        {
            return _direction;
        }

        public override void Update()
        {
            foreach (var targetType in _skill.GetTargetTypes())
            {
                var targets = EntityManager.GetEntities(targetType, this.GetLocation());
                if (targets != null && targets.Count > 0)
                {
                    hitTarget = targets[0];
                    if (null != hitTarget && hitTarget != this && hitTarget != _source)
                    {
                        _skill.Affect(hitTarget);
                        Cleanup(this);
                    }
                }
            }
            foreach (var targetType in _source.GetTargetActorTypes())
            {
                var targets = EntityManager.GetActorsAt(this.GetLocation(),targetType);
                if (targets != null && targets.Count > 0)
                {
                    hitTarget = targets[0];
                    if (null != hitTarget && hitTarget != this && hitTarget != _source)
                    {
                        _skill.Affect(hitTarget);
                        Cleanup(this);
                    }
                }
            }
            if(_currentStrength < .001)
            {
                Cleanup(_source);
            }
            else
            {
                _coolDown--;
                if (_coolDown <= 0)
                {
                    if (_startingStrength == 0)
                    {
                        _startingStrength = _currentStrength;
                    }
                    _currentStrength *= _strengthDecayAmount;
                    _velocity.SetX(_velocity.X * _currentStrength);
                    _velocity.SetY(_velocity.Y * _currentStrength);
                    _animation.Animate(this, _source, _velocity);
                    _isActive = _skill.AffectTarget(_source, this);
                    _coolDown = CoolDown;
                }
            }
        }
    }
}
