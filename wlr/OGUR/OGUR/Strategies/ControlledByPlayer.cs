using OGUR.Dungeons;
using OGUR.GameObjects;
using OGUR.Management;
using OGUR.Creatures;
namespace OGUR.Strategies
{
    public class ControlledByPlayer : IStrategy
    {
        public ControlledByPlayer(ICreature parent) : base(parent)
        {
        }

        public override void Act(ICreature target)
        {
            if (InputManager.IsPressed(InputManager.Commands.Start, target.GetPlayerIndex()))
            {
                target.SetPlaying(true);
            }
            if (InputManager.IsPressed(InputManager.Commands.Back, target.GetPlayerIndex()))
            {
                target.SetPlaying(false);
            }
            if (target.IsPlaying())
            {
                if (InputManager.IsContext(InputManager.Contexts.Free,target.GetPlayerIndex()))
                {
                    //Handle skills
                    var skillCycleVelocity =
                        ((InputManager.IsPressed(InputManager.Commands.CycleLeft, target.GetPlayerIndex()))
                             ? -1
                             : 0)
                        +
                        ((InputManager.IsPressed(InputManager.Commands.CycleRight, target.GetPlayerIndex()))
                             ? 1
                             : 0);
                    target.CycleActiveSkill(skillCycleVelocity);

                    //Handle movement
                    int leftVelocity = (InputManager.IsPressed(InputManager.Commands.MoveLeft,
                                                                   target.GetPlayerIndex())
                                                ? -target.GetInt(StatType.MOVE_SPEED)
                                                : 0);
                    var rightVelocity =
                        ((InputManager.IsPressed(InputManager.Commands.MoveRight, target.GetPlayerIndex()))
                             ? target.GetInt(StatType.MOVE_SPEED)
                             : 0);
                    var xVel = leftVelocity + rightVelocity;
                    var downVelocity =
                        ((InputManager.IsPressed(InputManager.Commands.MoveDown, target.GetPlayerIndex()))
                             ? target.GetInt(StatType.MOVE_SPEED)
                             : 0);
                    var upVelocity =
                        ((InputManager.IsPressed(InputManager.Commands.MoveUp, target.GetPlayerIndex()))
                             ? -target.GetInt(StatType.MOVE_SPEED)
                             : 0);
                    var yVel = downVelocity + upVelocity;
                    target.MoveIfPossible((int) xVel, (int) yVel);

                    foreach (var downstairs in GameplayObjectManager.GetObjects(GameObjectType.DOWNSTAIRS))
                    {
                        if (Collision.HitTest.IsTouching(downstairs, target) &&
                            InputManager.IsPressed(InputManager.Commands.Confirm, target.GetPlayerIndex()))
                        {
                            DungeonManager.GotoNext();
                        }
                    }
                    foreach (var upstairs in GameplayObjectManager.GetObjects(GameObjectType.UPSTAIRS))
                    {
                        if (Collision.HitTest.IsTouching(upstairs, target) &&
                            InputManager.IsPressed(InputManager.Commands.Confirm, target.GetPlayerIndex()))
                        {
                            DungeonManager.GotoPrevious();
                        }
                    }
                }
                if (InputManager.IsPressed(InputManager.Commands.Inventory, target.GetPlayerIndex()))
                {
                    InputManager.SetContext(
                        target.ToggleInventoryVisibility()
                            ? InputManager.Contexts.Inventory
                            : InputManager.Contexts.Free, target.GetPlayerIndex());
                }
            }
        }
    }
}