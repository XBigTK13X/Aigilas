using System;
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
        private Point2 m_keyVelocity = new Point2(0, 0);

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
                var leftVelocity = (InputManager.IsPressed(InputManager.Commands.MoveLeft,target.GetPlayerIndex())? -target.Get(StatType.MOVE_SPEED): 0);
                var rightVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveRight, target.GetPlayerIndex()))? target.Get(StatType.MOVE_SPEED): 0);
                m_keyVelocity.SetX(rightVelocity + leftVelocity);
                
                var downVelocity = ((InputManager.IsPressed(InputManager.Commands.MoveDown, target.GetPlayerIndex()))? target.Get(StatType.MOVE_SPEED): 0);
                var upVelocity =((InputManager.IsPressed(InputManager.Commands.MoveUp, target.GetPlayerIndex()))? -target.Get(StatType.MOVE_SPEED): 0);
                m_keyVelocity.SetY(upVelocity + downVelocity);

                if (InputManager.IsContext(InputManager.Contexts.Free,target.GetPlayerIndex()))
                {
                    var skillCycleVelocity =
                        ((InputManager.IsPressed(InputManager.Commands.CycleLeft, target.GetPlayerIndex()))? -1: 0)
                        +
                        ((InputManager.IsPressed(InputManager.Commands.CycleRight, target.GetPlayerIndex()))? 1: 0);
                    target.CycleActiveSkill(skillCycleVelocity);

                    if (!m_isCasting)
                    {
                        target.MoveIfPossible(m_keyVelocity.X, m_keyVelocity.Y);
                    }
                    var isPress = InputManager.IsPressed(InputManager.Commands.Confirm, target.GetPlayerIndex());
                    if (!isPress)
                    {
                        target.SetInteraction(false);
                    }
                    if (isPress && !target.IsInteracting())
                    {
                        target.SetInteraction(true);
                    }
                }
                if (InputManager.IsPressed(InputManager.Commands.Skill, target.GetPlayerIndex()))
                {
                    m_isCasting = true;
                }

                if(m_isCasting)
                {
                    if (!m_keyVelocity.IsZero())
                    {
                        target.SetSkillVector(m_keyVelocity);
                    }
                    if (target.GetSkillVector() == null)
                    {
                        target.SetSkillVector(new Point2(1, 0));
                    }
                    if (!target.GetSkillVector().IsZero())
                    {
                        target.UseActiveSkill();
                        m_isCasting = false;
                    }
                }
                
                if (InputManager.IsPressed(InputManager.Commands.Inventory, target.GetPlayerIndex()))
                {
                    InputManager.SetContext(target.ToggleInventoryVisibility()? InputManager.Contexts.Inventory: InputManager.Contexts.Free, target.GetPlayerIndex());
                }
            }
        }
    }
}