package aigilas.entities;

import aigilas.Common;
import aigilas.creatures.BaseCreature;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillFactory;
import aigilas.skills.animations.SkillAnimation;
import sps.bridge.ActorType;
import sps.bridge.DrawDepths;
import sps.bridge.EntityType;
import sps.bridge.EntityTypes;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.entities.IActor;
import sps.particles.ParticleEngine;
import sps.particles.behaviors.FollowBehavior;

import java.util.List;

public class SkillEffect extends Entity {
    private static final float _strengthDecayAmount = .75f;
    public static final float DefaultStrength = 1;
    private static final float CoolDown = 5;

    private final Point2 _velocity = new Point2(0, 0);
    private final BaseCreature _source;
    private final BaseSkill _skill;
    private float _currentStrength = 0;
    private float _startingStrength = 0;
    private final SkillAnimation _animation;
    private float _coolDown = CoolDown;
    private final Point2 _direction = new Point2(0, 0);

    public SkillEffect(Point2 gridLocation, Point2 velocity, BaseCreature source, BaseSkill skill) {
        _skill = skill;
        initialize(gridLocation, _skill.behavior().getSpriteType(), EntityTypes.get(Common.Skill_Effect), DrawDepths.get(Common.Base_Skill_Effect));
        _velocity.copy(velocity);
        _direction.copy(velocity);
        _source = source;
        _startingStrength = _currentStrength = _skill.components().getStrength();
        _animation = SkillFactory.create(_skill.behavior().getAnimationType());
        _graphic.setColor(skill.components().getColor());
        _graphic.setAlpha(0);
        ParticleEngine.emit(FollowBehavior.getInstance(), this, _graphic.getColor());
    }

    private Entity hitTarget;

    public void cleanup(Entity target) {
        setInactive();
        _skill.cleanup(target, this);
    }

    public Point2 getDirection() {
        return _direction;
    }

    @Override
    public void update() {
        for (EntityType targetType : _skill.components().getTargetTypes()) {
            List<Entity> targets = EntityManager.get().getEntities(targetType, this.getLocation());
            if (targets != null && targets.size() > 0) {
                hitTarget = targets.get(0);
                if (null != hitTarget && hitTarget != this && hitTarget != _source && _skill.components().onlyAffects(hitTarget.getEntityType())) {
                    _skill.affect(hitTarget);
                    cleanup(this);
                }
            }
        }
        for (ActorType targetType : _source.getTargetActorTypes()) {
            List<IActor> targets = EntityManager.get().getActorsAt(this.getLocation(), targetType);
            if (targets != null && targets.size() > 0) {
                hitTarget = (Entity) targets.get(0);
                if (null != hitTarget && hitTarget != this && hitTarget != _source && _skill.components().onlyAffects(hitTarget.getEntityType())) {
                    _skill.affect(hitTarget);
                    cleanup(this);
                }
            }
        }
        if (_currentStrength < .001) {
            cleanup(_source);
        }
        else {
            _coolDown--;
            if (_coolDown <= 0) {
                if (_startingStrength == 0) {
                    _startingStrength = _currentStrength;
                }
                _currentStrength *= _strengthDecayAmount;
                _velocity.setX(_velocity.X * _currentStrength);
                _velocity.setY(_velocity.Y * _currentStrength);
                _animation.animate(this, _source, _velocity);
                _isActive = _skill.behavior().affectTarget(_source, this);
                _coolDown = CoolDown;
            }
        }
    }
}
