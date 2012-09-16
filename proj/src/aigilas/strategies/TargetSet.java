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

    public ICreature AddTarget(ICreature target)

    {
        _targets.add(target);
        return target;
    }

    public void AddTargetTypes(ActorType... actorTypes) {
        for (ActorType type : actorTypes) {
            _targetActorTypes.add(type);
        }
    }

    public void AddEntityTypes(int... entityTypes) {
        for (int type : entityTypes) {
            _targetEntityTypes.add(type);
        }
    }

    public void AddTargets(ICreature source)

    {
        _targets.addAll(source.GetTargets()._targets);
        _targetActorTypes.addAll(source.GetTargets()._targetActorTypes);
    }

    public List<ICreature> AddTargets(List<ICreature> targets)

    {
        if (targets != null) {
            _targets.addAll(targets);
        }
        return targets;
    }

    private List<ICreature> _calculatedTargets;
    private float dist;
    ICreature closest;

    public ICreature FindClosest() {
        closest = null;
        float closestDistance = Float.MAX_VALUE;
        if (_targets != null) {
            for (ICreature target : _targets) {
                dist = Point2.DistanceSquared(target.GetLocation(), _parent.GetLocation());
                if (dist < closestDistance) {
                    closest = target;
                    closestDistance = dist;
                }
            }
            for (ActorType actorType : _targetActorTypes) {
                _calculatedTargets = new ArrayList<ICreature>();
                for (IEntity entity : EntityManager.GetActors(actorType)) {
                    _calculatedTargets.add((ICreature) entity);
                }

                for (ICreature creature : _calculatedTargets) {
                    if (creature != _parent) {
                        dist = Point2.DistanceSquared(creature.GetLocation(), _parent.GetLocation());
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

    public IEntity GetCollidedTarget(Entity source)

    {
        for (ICreature target : _targets) {
            if (target.contains(source.GetLocation())) {
                return target;
            }
        }

        for (ActorType actorType : _targetActorTypes) {
            for (IActor target : EntityManager.GetActorsAt(source.GetLocation())) {
                if (target.GetActorType() == actorType || (actorType == ActorType.NONPLAYER && target.GetActorType() != ActorType.PLAYER) || (actorType == ActorType.PLAYER && target.GetActorType() == ActorType.PLAYER)) {
                    return target;
                }
            }
        }
        return null;
    }

    public List<ActorType> GetTargetActorTypes() {
        return _targetActorTypes;
    }
}
