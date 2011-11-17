using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Dungeons;
using OGUR.Paths;

namespace OGUR.Strategies
{
    public class AttackPlayers : IStrategy
    {
        public AttackPlayers(ICreature parent)
            : base(parent)
        {
            m_targets.AddTargetTypes(CreatureType.PLAYER);
        }

        private ICreature opponent;
        private readonly Path targetPath = new Path();
        private readonly Point2 nextMove = new Point2(0, 0);
        private const int throttleMin = 10;
        private const int throttleMax = 20;
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
                    targetPath.Copy(PathFinder.FindNextMove(target.GetLocation(), opponent.GetLocation()));
                }
                throttle = rand.Next(throttleMin, throttleMax);
            }
            if (null != targetPath)
            {
                if (targetPath.HasMoves())
                {
                    if (target.Get(StatType.MOVE_COOL_DOWN) >= target.GetMax(StatType.MOVE_COOL_DOWN))
                    {
                        nextMove.Copy(targetPath.GetNextMove());
                        target.MoveTo(nextMove);
                    }
                }
            }
        }
    }
}