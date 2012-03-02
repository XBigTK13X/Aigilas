using System;
using OGUR.Creatures;
using SPX.Core;
using SPX.Paths;

namespace OGUR.Strategies
{
    public abstract class IStrategy
    {
        protected TargetSet m_targets;
        protected ICreature m_parent;
        
        protected const int throttleMin = 10;
        protected const int throttleMax = 20;
        protected int throttle = 0;

        protected ICreature opponent;
        protected readonly Path targetPath = new Path();
        protected readonly Point2 nextMove = new Point2(0, 0);
        protected static readonly Random rand = new Random();

        protected IStrategy(ICreature parent)
        {
            m_targets = new TargetSet(parent);
            m_parent = parent;
        }
        public abstract void Act();

        public TargetSet GetTargets()
        {
            return m_targets;
        }
        public void AddTargets(ICreature source)
        {
            m_targets.AddTargets(source);
        }

        protected bool AbleToMove()
        {
            throttle--;
            if (throttle <= 0)
            {
                opponent = m_targets.FindClosest();
                //Every player is dead
                if (null != opponent)
                {
                    targetPath.Copy(PathFinder.FindNextMove(m_parent.GetLocation(), opponent.GetLocation()));
                }
                throttle = rand.Next(throttleMin, throttleMax);
            }
            if (null != targetPath)
            {
                if (targetPath.HasMoves() && m_parent.Get(StatType.MOVE_COOL_DOWN) >= m_parent.GetMax(StatType.MOVE_COOL_DOWN))
                {
                    return true;
                }
            }
            return false;
        }

        protected readonly Point2 diff = new Point2(0, 0);
        protected Point2 CalculateTargetVector(Point2 source, Point2 dest)
        {
            diff.SetX(source.GridX - dest.GridX);
            diff.SetY(source.GridY - dest.GridY);
            if (diff.GridY == 0)
            {
                if (diff.GridX > 0)
                {
                    diff.SetX(-1f);
                }
                else
                {
                    diff.SetX(1f);
                }
            }
            else if (diff.GridX == 0)
            {
                if (diff.GridY > 0)
                {
                    diff.SetY(-1f);
                }
                else
                {
                    diff.SetY(1f);
                }
            }
            else
            {
                diff.SetX(0);
                diff.SetY(0);
            }
            return diff;
        }
    }
}