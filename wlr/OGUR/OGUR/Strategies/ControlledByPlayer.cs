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
            decimal xVel = ((InputManager.IsPressed(InputManager.Commands.MoveLeft, target.GetPlayerIndex())) ? 
                            -target.GetStat(Stat.MOVE_SPEED) : 0) 
                       +
                       ((InputManager.IsPressed(InputManager.Commands.MoveRight, target.GetPlayerIndex())) ? 
                            target.GetStat(Stat.MOVE_SPEED) : 0);
            decimal yVel = ((InputManager.IsPressed(InputManager.Commands.MoveDown, target.GetPlayerIndex())) ?
                            target.GetStat(Stat.MOVE_SPEED) : 0) 
                       +
                       ((InputManager.IsPressed(InputManager.Commands.MoveUp, target.GetPlayerIndex())) ?
                            -target.GetStat(Stat.MOVE_SPEED) : 0);
            target.MoveIfPossible((int)xVel,(int)yVel);
        }
    }
}
