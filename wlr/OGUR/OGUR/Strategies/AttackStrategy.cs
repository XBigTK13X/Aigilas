using OGUR.Creatures;
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
                _targets.AddTargetTypes(targetType);
            }
        }

        public override void Act()
        {
            if (AbleToMove())
            {
                if (SkillFactory.IsSkill(_parent.GetActiveSkillName(), AnimationType.RANGED))
                {
                    _parent.SetSkillVector(CalculateTargetVector(_parent.GetLocation(), opponent.GetLocation()));
                    if (_parent.GetSkillVector().GridX != 0 || _parent.GetSkillVector().GridY != 0)
                    {
                        _parent.UseActiveSkill();
                    }
                }
                if (targetPath.HasMoves())
                {
                    nextMove.Copy(targetPath.GetNextMove());
                    _parent.MoveTo(nextMove);
                }
            }
        }
    }
}