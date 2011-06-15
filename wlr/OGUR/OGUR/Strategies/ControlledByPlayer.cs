using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Management;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    class ControlledByPlayer:IStrategy
    {
        public override void Act(ICreature target)
        {
            decimal leftVelocity = (InputManager.IsPressed(InputManager.Commands.MoveLeft, target.GetPlayerIndex()) ? -target.GetStat(Stat.MOVE_SPEED) : 0);
            decimal rightVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveRight, target.GetPlayerIndex())) ?target.GetStat(Stat.MOVE_SPEED) : 0);
            decimal xVel = leftVelocity + rightVelocity;
            decimal downVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveDown, target.GetPlayerIndex()))?target.GetStat(Stat.MOVE_SPEED) : 0);                             
            decimal upVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveUp, target.GetPlayerIndex())) ?-target.GetStat(Stat.MOVE_SPEED) : 0);
            decimal yVel = downVelocity + upVelocity;
            target.MoveIfPossible((int)xVel,(int)yVel);
        }
    }
}
