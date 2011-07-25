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
                var leftVelocity = (opponent.GetPosition().X < target.GetPosition().X)
                                           ? -target.Get(StatType.MOVE_SPEED)
                                           : 0;
                var rightVelocity = (opponent.GetPosition().X > target.GetPosition().X)
                                            ? target.Get(StatType.MOVE_SPEED)
                                            : 0;
                var xVel = leftVelocity + rightVelocity;
                var downVelocity = (opponent.GetPosition().Y > target.GetPosition().Y)
                                           ? target.Get(StatType.MOVE_SPEED)
                                           : 0;
                var upVelocity = (opponent.GetPosition().Y < target.GetPosition().Y)
                                         ? -target.Get(StatType.MOVE_SPEED)
                                         : 0;
                var yVel = downVelocity + upVelocity;
                target.MoveIfPossible((int) xVel, (int) yVel);
            }
        }
    }
}