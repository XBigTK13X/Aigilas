package aigilas.strategies;

import aigilas.creatures.BaseCreature;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;
import sps.core.Core;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.entities.IActor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TargetSet

{
    private final List<BaseCreature> _targets = new ArrayList<BaseCreature>();
    private final List<ActorType> _targetActorTypes = new ArrayList<ActorType>();
    private final List<Integer> _targetEntityTypes = new ArrayList<Integer>();
    private final BaseCreature _parent;

    public TargetSet(BaseCreature parent)

    {
        _parent = parent;
    }

    public BaseCreature addTarget(BaseCreature target)

    {
        _targets.add(target);
        return target;
    }

    public void addTargetTypes(ActorType... actorTypes) {
        Collections.addAll(_targetActorTypes, actorTypes);
    }

    public void addEntityTypes(int... entityTypes) {
        for (int type : entityTypes) {
            _targetEntityTypes.add(type);
        }
    }

    public void addTargets(BaseCreature source)

    {
        _targets.addAll(source.getTargets()._targets);
        _targetActorTypes.addAll(source.getTargets()._targetActorTypes);
    }

    public List<BaseCreature> addTargets(List<BaseCreature> targets)

    {
        if (targets != null) {
            _targets.addAll(targets);
        }
        return targets;
    }

    private List<BaseCreature> _calculatedTargets;
    private float dist;
    BaseCreature closest;

    public BaseCreature findClosest() {
        closest = null;
        float closestDistance = Float.MAX_VALUE;
        if (_targets != null) {
            for (BaseCreature target : _targets) {
                dist = Point2.distanceSquared(target.getLocation(), _parent.getLocation());
                if (dist < closestDistance) {
                    closest = target;
                    closestDistance = dist;
                }
            }
            for (ActorType actorType : _targetActorTypes) {
                _calculatedTargets = new ArrayList<BaseCreature>();
                for (IActor entity : EntityManager.get().getActors(actorType)) {
                    _calculatedTargets.add((BaseCreature) entity);
                }

                for (BaseCreature creature : _calculatedTargets) {
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

    public Entity getCollidedTarget(Entity source)

    {
        for (BaseCreature target : _targets) {
            if (target.contains(source.getLocation())) {
                return target;
            }
        }

        for (ActorType actorType : _targetActorTypes) {
            for (IActor target : EntityManager.get().getActorsAt(source.getLocation())) {
                if (target.getActorType() == actorType || (actorType == ActorTypes.get(Core.Non_Player) && target.getActorType() != ActorTypes.get(Core.Player)) || (actorType == ActorTypes.get(Core.Player) && target.getActorType() == ActorTypes.get(Core.Player))) {
                    return (Entity) target;
                }
            }
        }
        return null;
    }

    public List<ActorType> getTargetActorTypes() {
        return _targetActorTypes;
    }
}
