using System;
using OGUR.Creatures;
using OGUR.GameObjects;

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
                float leftVelocity = (opponent.GetPosition().X < target.GetPosition().X)
                                           ? -target.GetInt(StatType.MOVE_SPEED)
                                           : 0;
                float rightVelocity = (opponent.GetPosition().X > target.GetPosition().X)
                                            ? target.GetInt(StatType.MOVE_SPEED)
                                            : 0;
                float xVel = leftVelocity + rightVelocity;
                float downVelocity = (opponent.GetPosition().Y > target.GetPosition().Y)
                                           ? target.GetInt(StatType.MOVE_SPEED)
                                           : 0;
                float upVelocity = (opponent.GetPosition().Y < target.GetPosition().Y)
                                         ? -target.GetInt(StatType.MOVE_SPEED)
                                         : 0;
                float yVel = downVelocity + upVelocity;
                target.MoveIfPossible((int) xVel, (int) yVel);
            }
        }
    }
}