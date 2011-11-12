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
            if (InputManager.IsPressed(Commands.Start, target.GetPlayerIndex()))
            {
                target.SetPlaying(true);
            }
            if (InputManager.IsPressed(Commands.Back, target.GetPlayerIndex()))
            {
                target.SetPlaying(false);
            }
            if (target.IsPlaying())
            {
                var leftVelocity = (InputManager.IsPressed(Commands.MoveLeft,target.GetPlayerIndex())? -Stats.DefaultMoveSpeed: 0);
                var rightVelocity = ((InputManager.IsPressed(Commands.MoveRight, target.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                m_keyVelocity.SetX(rightVelocity + leftVelocity);

                var downVelocity = ((InputManager.IsPressed(Commands.MoveDown, target.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                var upVelocity = ((InputManager.IsPressed(Commands.MoveUp, target.GetPlayerIndex())) ? -Stats.DefaultMoveSpeed : 0);
                m_keyVelocity.SetY(upVelocity + downVelocity);

                if (InputManager.IsContext(Contexts.Free,target.GetPlayerIndex()))
                {
                    var skillCycleVelocity =
                        ((InputManager.IsPressed(Commands.CycleLeft, target.GetPlayerIndex()))? -1: 0)
                        +
                        ((InputManager.IsPressed(Commands.CycleRight, target.GetPlayerIndex()))? 1: 0);
                    target.CycleActiveSkill(skillCycleVelocity);

                    if (!m_isCasting)
                    {
                        target.MoveIfPossible(m_keyVelocity.X, m_keyVelocity.Y);
                    }
                    var isPress = InputManager.IsPressed(Commands.Confirm, target.GetPlayerIndex());
                    if (!isPress)
                    {
                        target.SetInteraction(false);
                    }
                    if (isPress && !target.IsInteracting())
                    {
                        target.SetInteraction(true);
                    }
                }
                if (InputManager.IsPressed(Commands.Skill, target.GetPlayerIndex()))
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
                
                if (InputManager.IsPressed(Commands.Inventory, target.GetPlayerIndex()))
                {
                    InputManager.SetContext(target.ToggleInventoryVisibility()? Contexts.Inventory: Contexts.Free, target.GetPlayerIndex());
                }
            }
        }
    }
}