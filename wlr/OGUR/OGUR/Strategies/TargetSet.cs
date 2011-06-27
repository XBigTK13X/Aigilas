using System.Collections.Generic;
using Microsoft.Xna.Framework;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Strategies
{
    public class TargetSet
    {
        private List<ICreature> m_targets = new List<ICreature>();
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
                    var dist = Vector2.DistanceSquared(target.GetPosition(), m_parent.GetPosition());
                    if (dist < closestDistance)
                    {
                        result = target;
                        closestDistance = dist;
                    }
                }
            }
            return result;
        }

        public void UpdateTypes(CreatureType type)
        {
            m_targets = new List<ICreature>(GameplayObjectManager.GetObjects(type));
        }
    }
}
