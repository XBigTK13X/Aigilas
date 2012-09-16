package aigilas.strategies;

import aigilas.creatures.ICreature;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.entities.Entity;
import spx.entities.EntityManager;
import spx.entities.IActor;
import spx.entities.IEntity;

import java.util.ArrayList;
import java.util.List;

public class TargetSet

{
    private List<ICreature> _targets = new ArrayList<>();
    private List<ActorType> _targetActorTypes = new ArrayList<>();
    private List<Integer> _targetEntityTypes = new ArrayList<>();
    private ICreature _parent;

    public TargetSet(ICreature parent)

    {
        _parent = parent;
    }

    public ICreature addTarget(ICreature target)

    {
        _targets.add(target);
        return target;
    }

    public void addTargetTypes(ActorType... actorTypes) {
        for (ActorType type : actorTypes) {
            _targetActorTypes.add(type);
        }
    }

    public void addEntityTypes(int... entityTypes) {
        for (int type : entityTypes) {
            _targetEntityTypes.add(type);
        }
    }

    public void addTargets(ICreature source)

    {
        _targets.addAll(source.getTargets()._targets);
        _targetActorTypes.addAll(source.getTargets()._targetActorTypes);
    }

    public List<ICreature> addTargets(List<ICreature> targets)

    {
        if (targets != null) {
            _targets.addAll(targets);
        }
        return targets;
    }

    private List<ICreature> _calculatedTargets;
    private float dist;
    ICreature closest;

    public ICreature findClosest() {
        closest = null;
        float closestDistance = Float.MAX_VALUE;
        if (_targets != null) {
            for (ICreature target : _targets) {
                dist = Point2.distanceSquared(target.getLocation(), _parent.getLocation());
                if (dist < closestDistance) {
                    closest = target;
                    closestDistance = dist;
                }
            }
            for (ActorType actorType : _targetActorTypes) {
                _calculatedTargets = new ArrayList<ICreature>();
                for (IEntity entity : EntityManager.getActors(actorType)) {
                    _calculatedTargets.add((ICreature) entity);
                }

                for (ICreature creature : _calculatedTargets) {
                    if (creature != _parent) {
                        dist = Point2.distanceSquared(creature.getLocation(), _parent.getLocation());
                        if (dist < closestDistance) {
                            closest = creature;
                            closestDistance = dist;
                        }
                    }
                }
            }
        }
        return closest;
    }

    public IEntity getCollidedTarget(Entity source)

    {
        for (ICreature target : _targets) {
            if (target.contains(source.getLocation())) {
                return target;
            }
        }

        for (ActorType actorType : _targetActorTypes) {
            for (IActor target : EntityManager.getActorsAt(source.getLocation())) {
                if (target.getActorType() == actorType || (actorType == ActorType.NONPLAYER && target.getActorType() != ActorType.PLAYER) || (actorType == ActorType.PLAYER && target.getActorType() == ActorType.PLAYER)) {
                    return target;
                }
            }
        }
        return null;
    }

    public List<ActorType> getTargetActorTypes() {
        return _targetActorTypes;
    }
}
