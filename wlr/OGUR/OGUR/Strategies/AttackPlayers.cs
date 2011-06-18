using System;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Strategies
{
    public class AttackPlayers : IStrategy
    {
        public override void Act(ICreature target)
        {
            var opponent = GameplayObjectManager.GetNearestPlayer(target);
            decimal leftVelocity = (opponent.GetPosition().X<target.GetPosition().X)
                                        ? -target.GetInt(StatType.MOVE_SPEED)
                                        : 0;
            decimal rightVelocity = (opponent.GetPosition().X>target.GetPosition().X)
                                         ? target.GetInt(StatType.MOVE_SPEED)
                                         : 0;
            decimal xVel = leftVelocity + rightVelocity;
            decimal downVelocity = (opponent.GetPosition().Y>target.GetPosition().Y)
                                        ? target.GetInt(StatType.MOVE_SPEED)
                                        : 0;
            decimal upVelocity = (opponent.GetPosition().Y<target.GetPosition().Y)
                                      ? -target.GetInt(StatType.MOVE_SPEED)
                                      : 0;
            decimal yVel = downVelocity + upVelocity;
            target.MoveIfPossible((int)xVel, (int)yVel);
        }
    }
}