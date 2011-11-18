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

        public override void Act()
        {
            if (InputManager.IsPressed(Commands.Start, m_parent.GetPlayerIndex()))
            {
                m_parent.SetPlaying(true);
            }
            if (InputManager.IsPressed(Commands.Back, m_parent.GetPlayerIndex()))
            {
                m_parent.SetPlaying(false);
            }
            if (m_parent.IsPlaying())
            {
                var leftVelocity = (InputManager.IsPressed(Commands.MoveLeft,m_parent.GetPlayerIndex())? -Stats.DefaultMoveSpeed: 0);
                var rightVelocity = ((InputManager.IsPressed(Commands.MoveRight, m_parent.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                m_keyVelocity.SetX(rightVelocity + leftVelocity);

                var downVelocity = ((InputManager.IsPressed(Commands.MoveDown, m_parent.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                var upVelocity = ((InputManager.IsPressed(Commands.MoveUp, m_parent.GetPlayerIndex())) ? -Stats.DefaultMoveSpeed : 0);
                m_keyVelocity.SetY(upVelocity + downVelocity);

                if (InputManager.IsContext(Contexts.Free,m_parent.GetPlayerIndex()))
                {
                    var skillCycleVelocity =
                        ((InputManager.IsPressed(Commands.CycleLeft, m_parent.GetPlayerIndex()))? -1: 0)
                        +
                        ((InputManager.IsPressed(Commands.CycleRight, m_parent.GetPlayerIndex()))? 1: 0);
                    m_parent.CycleActiveSkill(skillCycleVelocity);

                    if (!m_isCasting)
                    {
                        m_parent.MoveIfPossible(m_keyVelocity.X, m_keyVelocity.Y);
                    }
                    var isPress = InputManager.IsPressed(Commands.Confirm, m_parent.GetPlayerIndex());
                    if (!isPress)
                    {
                        m_parent.SetInteraction(false);
                    }
                    if (isPress && !m_parent.IsInteracting())
                    {
                        m_parent.SetInteraction(true);
                    }
                }
                if (InputManager.IsPressed(Commands.Skill, m_parent.GetPlayerIndex()))
                {
                    m_isCasting = true;
                }

                if(m_isCasting)
                {
                    if (!m_keyVelocity.IsZero())
                    {
                        m_parent.SetSkillVector(m_keyVelocity);
                    }
                    if (m_parent.GetSkillVector() == null)
                    {
                        m_parent.SetSkillVector(new Point2(1, 0));
                    }
                    if (!m_parent.GetSkillVector().IsZero())
                    {
                        m_parent.UseActiveSkill();
                        m_isCasting = false;
                    }
                }
                
                if (InputManager.IsPressed(Commands.Inventory, m_parent.GetPlayerIndex()))
                {
                    InputManager.SetContext(m_parent.ToggleInventoryVisibility()? Contexts.Inventory: Contexts.Free, m_parent.GetPlayerIndex());
                }
            }
        }
    }
}