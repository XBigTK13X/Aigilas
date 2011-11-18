using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Dungeons;
using OGUR.Paths;
using OGUR.Skills;

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

        public override void Act()
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
                if (m_parent.Get(StatType.MOVE_COOL_DOWN) >= m_parent.GetMax(StatType.MOVE_COOL_DOWN))
                {
                    if (SkillFactory.IsSkill(m_parent.GetActiveSkillName(), AnimationType.RANGED))
                    {
                        m_parent.SetSkillVector(CalculateTargetVector(m_parent.GetLocation(), opponent.GetLocation()));
                        if (m_parent.GetSkillVector().GridX != 0 || m_parent.GetSkillVector().GridY != 0)
                        {
                            m_parent.UseActiveSkill();
                        }
                    }
                    if (targetPath.HasMoves())
                    {
                        nextMove.Copy(targetPath.GetNextMove());
                        m_parent.MoveTo(nextMove);
                    }
                }
            }
        }

        private readonly Point2 diff = new Point2(0,0);
        private Point2 CalculateTargetVector(Point2 source, Point2 dest)
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