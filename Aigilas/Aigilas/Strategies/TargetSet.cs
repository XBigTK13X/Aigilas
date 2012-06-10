using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using SPX.Entities;
using Aigilas.Creatures;
using Aigilas.Entities;
using SPX.Core;

namespace Aigilas.Strategies
{
    public class TargetSet
    {
        private List<ICreature> _targets = new List<ICreature>();
        private List<int> _targetActorTypes = new List<int>();
        private List<int> _targetEntityTypes = new List<int>();
        private readonly ICreature _parent;

        public TargetSet(ICreature parent)
        {
            _parent = parent;
        }

        public ICreature AddTarget(ICreature target)
        {
            _targets.Add(target);
            return target;
        }

        public void AddTargetTypes(params int[] actorTypes)
        {
            foreach(var type in actorTypes)
            {
                _targetActorTypes.Add(type);
            }
        }

        public void AddEntityTypes(params int[] entityTypes)
        {
            foreach (var type in entityTypes)
            {
                _targetEntityTypes.Add(type);
            }
        }

        public void AddTargets(ICreature source)
        {
            _targets.AddRange(source.GetTargets()._targets);
            _targetActorTypes.AddRange(source.GetTargets()._targetActorTypes);
        }

        public IEnumerable<ICreature> AddTargets(IEnumerable<ICreature> targets)
        {
            if (targets != null)
            {
                _targets.AddRange(targets);
            }
            return targets;
        }

        private List<ICreature> _calculatedTargets;
        private float dist;
        ICreature closest;
        public ICreature FindClosest()
        {
            closest = null;
            var closestDistance = float.MaxValue;
            if (_targets != null)
            {
                foreach (var target in _targets)
                {
                    dist = Point2.DistanceSquared(target.GetLocation(), _parent.GetLocation());
                    if (dist < closestDistance)
                    {
                        closest = target;
                        closestDistance = dist;
                    }
                }
                foreach(var actorType in _targetActorTypes)
                {
                    _calculatedTargets = EntityManager.GetActors(actorType).Select(a=>a as ICreature).ToList();
                    foreach(var creature in _calculatedTargets)
                    {
                        if (creature != _parent)
                        {
                            dist = Point2.DistanceSquared(creature.GetLocation(), _parent.GetLocation());
                            if (dist < closestDistance)
                            {
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
            foreach(var target in _targets)
            {
                if(target.Contains(source.GetLocation()))
                {
                    return target;
                }
            }

            foreach (var actorType in _targetActorTypes)
            {
                foreach (var target in EntityManager.GetActorsAt(source.GetLocation()))
                {
                    if (target.GetActorType() == actorType ||
                        (actorType == ActorType.NONPLAYER && target.GetActorType() != ActorType.PLAYER) ||
                        (actorType == ActorType.PLAYER && target.GetActorType() == ActorType.PLAYER))
                    {
                        return target as IEntity;
                    }
                }
            }
            return null;
        }

        internal IEnumerable<int> GetTargetActorTypes()
        {
            return _targetActorTypes;
        }
    }
}
