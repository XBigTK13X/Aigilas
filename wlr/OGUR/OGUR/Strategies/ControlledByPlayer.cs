using OGUR.Collision;
using OGUR.Dungeons;
using OGUR.GameObjects;
using OGUR.Management;
using OGUR.Creatures;
namespace OGUR.Strategies
{
    public class ControlledByPlayer : IStrategy
    {
        private bool m_isCasting = false;

        public ControlledByPlayer(ICreature parent) : base(parent)
        {
            m_targets.AddTargetTypes(CreatureType.NONPLAYER);
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
                    if (!m_isCasting)
                    {
                        target.MoveIfPossible((int) xVel, (int) yVel);
                    }

                    foreach (var downstairs in GameplayObjectManager.GetObjects(GameObjectType.DOWNSTAIRS))
                    {
                        var stairs = (Downstairs) downstairs;
                        if (Collision.HitTest.IsTouching(downstairs, target) &&
                            InputManager.IsPressed(InputManager.Commands.Confirm, target.GetPlayerIndex()))
                        {
                            DungeonFactory.GetNextFloor(stairs.GetTargetLocation());
                        }
                    }
                    foreach (var upstairs in GameplayObjectManager.GetObjects(GameObjectType.UPSTAIRS))
                    {
                        var stairs = (Upstairs)upstairs;
                        if (Collision.HitTest.IsTouching(upstairs, target) &&
                            InputManager.IsPressed(InputManager.Commands.Confirm, target.GetPlayerIndex()))
                        {

                            DungeonFactory.GetPreviousFloor(stairs.GetTargetLocation());
                        }
                    }
                }
                if(m_isCasting)
                {
                    if(xVel!=0||yVel!=0)
                    {
                        target.SetSkillVector(new Point2(xVel, yVel));
                        target.UseActiveSkill();
                        m_isCasting = false;
                    }
                    if(InputManager.IsPressed(InputManager.Commands.Skill,target.GetPlayerIndex()))
                    {
                        m_isCasting = !m_isCasting;
                    }
                }
                if (InputManager.IsPressed(InputManager.Commands.Inventory, target.GetPlayerIndex()))
                {
                    InputManager.SetContext(
                        target.ToggleInventoryVisibility()
                            ? InputManager.Contexts.Inventory
                            : InputManager.Contexts.Free, target.GetPlayerIndex());
                }
                if (InputManager.IsPressed(InputManager.Commands.Skill, target.GetPlayerIndex()))
                {
                    m_isCasting = !m_isCasting;

                }
            }
        }
    }
}