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

        private ICreature opponent;
        private Point2 targetPosition;
        public override void Act(ICreature target)
        {
            target.MoveTo(Point2.m_locations[2, 2]);
            return;
            opponent = m_targets.FindClosest();
            //Every player is dead
            if (null != opponent)
            {
                targetPosition = PathFinder.FindNextMove(target.GetLocation(), opponent.GetLocation());
                if (null != targetPosition)
                {
                    target.MoveTo(targetPosition);
                }
            }
        }
    }
}