using System;
using Agilas.Creatures;
using SPX.Core;
using SPX.Paths;
using System.Collections.Generic;

namespace Agilas.Strategies
{
    public abstract class IStrategy
    {
        protected TargetSet _targets;
        protected ICreature _parent;
        
        protected const int throttleMin = 10;
        protected const int throttleMax = 20;
        protected int throttle = 0;

        protected ICreature opponent;
        protected readonly Path targetPath = new Path();
        protected readonly Point2 nextMove = new Point2(0, 0);

        protected int _strategyId;

        protected IStrategy(ICreature parent,int strategyId)
        {
            _targets = new TargetSet(parent);
            _parent = parent;
        }
        public abstract void Act();

        public TargetSet GetTargets()
        {
            return _targets;
        }
        public void AddTargets(ICreature source)
        {
            _targets.AddTargets(source);
        }

        protected bool AbleToMove()
        {
            throttle--;
            if (throttle <= 0)
            {
                opponent = _targets.FindClosest();
                //Every player is dead
                if (null != opponent)
                {
                    targetPath.Copy(PathFinder.FindNextMove(_parent.GetLocation(), opponent.GetLocation()));
                }
                throttle = RNG.Rand.Next(throttleMin, throttleMax);
            }
            if (null != targetPath)
            {
                if (targetPath.HasMoves() && _parent.IsCooledDown())
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

        public int GetId()
        {
            return _strategyId;
        }

        public IEnumerable<int> GetTargetActorTypes()
        {
            return _targets.GetTargetActorTypes();
        }
    }
}