using System;
using SPX.Entities;
using OGUR.Dungeons;
using OGUR.Entities;
using OGUR.Management;
using OGUR.Creatures;
using SPX.Core;
namespace OGUR.Strategies
{
    public class ControlledByPlayer : IStrategy
    {
        private bool m_isCasting = false;
        private Point2 m_keyVelocity = new Point2(0, 0);

        public ControlledByPlayer(ICreature parent) : base(parent)
        {
            m_targets.AddTargetTypes(ActorType.NONPLAYER);
        }

        public override void Act()
        {
            if (Input.IsPressed(Commands.Start, m_parent.GetPlayerIndex()))
            {
                m_parent.SetPlaying(true);
            }
            if (Input.IsPressed(Commands.Back, m_parent.GetPlayerIndex()))
            {
                m_parent.SetPlaying(false);
            }
            if (m_parent.IsPlaying())
            {
                var leftVelocity = (Input.IsPressed(Commands.MoveLeft,m_parent.GetPlayerIndex())? -Stats.DefaultMoveSpeed: 0);
                var rightVelocity = ((Input.IsPressed(Commands.MoveRight, m_parent.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                m_keyVelocity.SetX(rightVelocity + leftVelocity);

                var downVelocity = ((Input.IsPressed(Commands.MoveDown, m_parent.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                var upVelocity = ((Input.IsPressed(Commands.MoveUp, m_parent.GetPlayerIndex())) ? -Stats.DefaultMoveSpeed : 0);
                m_keyVelocity.SetY(upVelocity + downVelocity);

                if (Input.IsContext(Contexts.Free,m_parent.GetPlayerIndex()))
                {
                    var skillCycleVelocity =
                        ((Input.IsPressed(Commands.CycleLeft, m_parent.GetPlayerIndex()))? -1: 0)
                        +
                        ((Input.IsPressed(Commands.CycleRight, m_parent.GetPlayerIndex()))? 1: 0);
                    m_parent.CycleActiveSkill(skillCycleVelocity);

                    if (!m_isCasting)
                    {
                        m_parent.MoveIfPossible(m_keyVelocity.X, m_keyVelocity.Y);
                    }
                    var isPress = Input.IsPressed(Commands.Confirm, m_parent.GetPlayerIndex());
                    if (!isPress)
                    {
                        m_parent.SetInteraction(false);
                    }
                    if (isPress && !m_parent.IsInteracting())
                    {
                        m_parent.SetInteraction(true);
                    }
                }
                if (Input.IsPressed(Commands.Skill, m_parent.GetPlayerIndex()))
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
                
                if (Input.IsPressed(Commands.Inventory, m_parent.GetPlayerIndex()))
                {
                    Input.SetContext(m_parent.ToggleInventoryVisibility()? Contexts.Inventory: Contexts.Free, m_parent.GetPlayerIndex());
                }
            }
        }
    }
}