using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Dungeons;
using OGUR.Path;

namespace OGUR.Strategies
{
    public class AttackPlayers : IStrategy
    {
        public AttackPlayers(ICreature parent) : base(parent)
        {
            m_targets.AddTargetTypes(CreatureType.PLAYER);
        }

        public override void Act(ICreature target)
        {
            var opponent = m_targets.FindClosest();
            //Every player is dead
            if (null != opponent)
            {
                var targetPosition = PathFinder.FindNextMove(target.GetLocation(), opponent.GetLocation());
                if (null != targetPosition)
                {
                    target.MoveTo(targetPosition);
                }
            }
        }
    }
}