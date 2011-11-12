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
        private readonly Point2 targetPosition = new Point2(0,0);
        private const int throttleMin = 5;
        private const int throttleMax = 10; 
        private int throttle = 0;
        private static readonly Random rand = new Random();

        public override void Act(ICreature target)
        {
            throttle--;
            if (throttle <= 0)
            {
                opponent = m_targets.FindClosest();
                //Every player is dead
                if (null != opponent)
                {
                    targetPosition.Copy(PathFinder.FindNextMove(target.GetLocation(), opponent.GetLocation()));
                }
                throttle = rand.Next(throttleMin, throttleMax);
            }
            if (null != targetPosition)
            {
                target.MoveTo(targetPosition);
            }
        }
    }
}