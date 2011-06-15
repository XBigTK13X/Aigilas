using OGUR.Management;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public class ControlledByPlayer : IStrategy
    {
        public override void Act(ICreature target)
        {
            decimal leftVelocity = (InputManager.IsPressed(InputManager.Commands.MoveLeft, target.GetPlayerIndex())
                                        ? -target.GetInt(StatType.MOVE_SPEED)
                                        : 0);
            decimal rightVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveRight, target.GetPlayerIndex()))
                                         ? target.GetInt(StatType.MOVE_SPEED)
                                         : 0);
            decimal xVel = leftVelocity + rightVelocity;
            decimal downVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveDown, target.GetPlayerIndex()))
                                        ? target.GetInt(StatType.MOVE_SPEED)
                                        : 0);
            decimal upVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveUp, target.GetPlayerIndex()))
                                      ? -target.GetInt(StatType.MOVE_SPEED)
                                      : 0);
            decimal yVel = downVelocity + upVelocity;
            target.MoveIfPossible((int) xVel, (int) yVel);
            if(!InputManager.IsPressed(InputManager.Commands.Confirm,target.GetPlayerIndex(),false))
            {
                InputManager.Unlock(InputManager.Commands.Confirm,target.GetPlayerIndex());
            }
        }
    }
}