using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Dungeons;
using OGUR.Paths;
using OGUR.Skills;

namespace OGUR.Strategies
{
    public class AttackStrategy : IStrategy
    {
        public AttackStrategy(ICreature parent,params int[] targetTypes)
            : base(parent)
        {
            foreach (var targetType in targetTypes)
            {
                m_targets.AddTargetTypes(targetType);
            }
        }

        public override void Act()
        {
            if (AbleToMove())
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
}