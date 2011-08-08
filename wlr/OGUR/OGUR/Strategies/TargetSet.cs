using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Strategies
{
    public class TargetSet
    {
        private List<ICreature> m_targets = new List<ICreature>();
        private List<CreatureType> m_targetTypes = new List<CreatureType>(); 
        private readonly ICreature m_parent;

        public TargetSet(ICreature parent)
        {
            m_parent = parent;
        }

        public ICreature AddTarget(ICreature target)
        {
            m_targets.Add(target);
            return target;
        }

        public void AddTargetTypes(params object[] types)
        {
            foreach(var type in types)
            {
                m_targetTypes.Add((CreatureType)type);
            }
        }


        public IEnumerable<ICreature> AddTargets(IEnumerable<ICreature> targets)
        {
            if (targets != null)
            {
                m_targets.AddRange(targets);
            }
            return targets;
        }

        public ICreature FindClosest()
        {
            ICreature result = null;
            var closestDistance = float.MaxValue;
            if (m_targets != null)
            {
                foreach (var target in m_targets)
                {
                    var dist = Point2.DistanceSquared(target.GetLocation(), m_parent.GetLocation());
                    if (dist < closestDistance)
                    {
                        result = target;
                        closestDistance = dist;
                    }
                }
                foreach(var creatureType in m_targetTypes)
                {
                    foreach(var creature in GameplayObjectManager.GetObjects(creatureType))
                    {
                        var dist = Point2.DistanceSquared(creature.GetLocation(), m_parent.GetLocation());
                        if (dist < closestDistance)
                        {
                            result = creature;
                            closestDistance = dist;
                        }
                    }
                }
            }
            return result;
        }

        public GameplayObject GetCollidedTarget(GameplayObject source)
        {
            var result = m_targets.FirstOrDefault(target => Collision.HitTest.IsTouching(target, source));
            if(result==null)
            {
                foreach (var creature in from creatureType in m_targetTypes from creature in GameplayObjectManager.GetObjects(creatureType) where Collision.HitTest.IsTouching(creature, source) select creature)
                {
                    return creature;
                }
            }
            return result;
        }
    }
}
