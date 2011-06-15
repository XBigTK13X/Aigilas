using System;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public class AttackPlayers : IStrategy
    {
        public override void Act(ICreature target)
        {
            var rand = new Random();
            int xVel = (rand.Next(0, 3) - 1)*(int)target.Get(StatType.MOVE_SPEED);
            int yVel = (rand.Next(0, 3) - 1)*(int)target.Get(StatType.MOVE_SPEED);
            target.MoveIfPossible(xVel,yVel);
        }
    }
}