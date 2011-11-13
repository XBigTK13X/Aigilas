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
        private List<int> m_targetTypes = new List<int>(); 
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

        public void AddTargetTypes(params int[] creatureTypes)
        {
            foreach(var type in creatureTypes)
            {
                m_targetTypes.Add(type);
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


        private List<ICreature> m_calculatedTargets;
        private float dist;
        ICreature closest;
        public ICreature FindClosest()
        {
            closest = null;
            var closestDistance = float.MaxValue;
            if (m_targets != null)
            {
                foreach (var target in m_targets)
                {
                    dist = Point2.DistanceSquared(target.GetLocation(), m_parent.GetLocation());
                    if (dist < closestDistance)
                    {
                        closest = target;
                        closestDistance = dist;
                    }
                }
                foreach(var creatureType in m_targetTypes)
                {
                    m_calculatedTargets = GameplayObjectManager.GetCreatures(creatureType);
                    foreach(var creature in m_calculatedTargets)
                    {
                        dist = Point2.DistanceSquared(creature.GetLocation(), m_parent.GetLocation());
                        if (dist < closestDistance)
                        {
                            closest = creature;
                            closestDistance = dist;
                        }
                    }
                }
            }
            return closest;
        }

        public GameplayObject GetCollidedTarget(GameplayObject source)
        {
            foreach(var target in m_targets)
            {
                if(target.Contains(source.GetLocation()))
                {
                    return target;
                }
            }

            foreach (var creatureType in m_targetTypes)
            {
                foreach (var target in GameplayObjectManager.GetCreaturesAt(m_parent.GetLocation()))
                {
                    if (target.GetCreatureType()==creatureType)
                    {
                        return target;
                    }
                }
            }
            return null;
        }
    }
}
