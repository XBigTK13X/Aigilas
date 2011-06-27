using System;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Strategies
{
    public class AttackPlayers : IStrategy
    {
        public AttackPlayers(ICreature parent) : base(parent)
        {
        }

        public override void Act(ICreature target)
        {
            m_targets.UpdateTypes(CreatureType.PLAYER);
            var opponent = m_targets.FindClosest();
            //Every player is dead
            if (null != opponent)
            {
                decimal leftVelocity = (opponent.GetPosition().X < target.GetPosition().X)
                                           ? -target.GetInt(StatType.MOVE_SPEED)
                                           : 0;
                decimal rightVelocity = (opponent.GetPosition().X > target.GetPosition().X)
                                            ? target.GetInt(StatType.MOVE_SPEED)
                                            : 0;
                decimal xVel = leftVelocity + rightVelocity;
                decimal downVelocity = (opponent.GetPosition().Y > target.GetPosition().Y)
                                           ? target.GetInt(StatType.MOVE_SPEED)
                                           : 0;
                decimal upVelocity = (opponent.GetPosition().Y < target.GetPosition().Y)
                                         ? -target.GetInt(StatType.MOVE_SPEED)
                                         : 0;
                decimal yVel = downVelocity + upVelocity;
                target.MoveIfPossible((int) xVel, (int) yVel);
            }
        }
    }
}