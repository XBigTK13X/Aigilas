using System;
using SPX.Entities;
using Aigilas.Dungeons;
using Aigilas.Entities;
using Aigilas.Management;
using Aigilas.Creatures;
using SPX.Core;
using System.Collections.Generic;
namespace Aigilas.Strategies
{
    public class ControlledByPlayer : IStrategy
    {
        private bool _isCasting = false;
        private Point2 _keyVelocity = new Point2(0, 0);

        private static readonly List<int> __hotkeys = new List<int>()
        {
            Commands.HotSkill1,
            Commands.HotSkill2,
            Commands.HotSkill3
        };

        public ControlledByPlayer(ICreature parent) : base(parent,Strategy.ControlledByPlayer)
        {
            _targets.AddTargetTypes(ActorType.NONPLAYER);
        }

        public override void Act()
        {
            if (Input.IsPressed(Commands.Start, _parent.GetPlayerIndex()))
            {
                _parent.SetPlaying(true);
            }
            if (Input.IsPressed(Commands.Back, _parent.GetPlayerIndex()))
            {
                _parent.SetPlaying(false);
            }
            if (_parent.IsPlaying())
            {
                if (!Input.IsContext(Contexts.Inventory,_parent.GetPlayerIndex()))
                {
                    var leftVelocity = (Input.IsPressed(Commands.MoveLeft, _parent.GetPlayerIndex()) ? -Stats.DefaultMoveSpeed : 0);
                    var rightVelocity = ((Input.IsPressed(Commands.MoveRight, _parent.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                    _keyVelocity.SetX(rightVelocity + leftVelocity);

                    var downVelocity = ((Input.IsPressed(Commands.MoveDown, _parent.GetPlayerIndex())) ? Stats.DefaultMoveSpeed : 0);
                    var upVelocity = ((Input.IsPressed(Commands.MoveUp, _parent.GetPlayerIndex())) ? -Stats.DefaultMoveSpeed : 0);
                    _keyVelocity.SetY(upVelocity + downVelocity);

                    if (Input.IsContext(Contexts.Free, _parent.GetPlayerIndex()))
                    {
                        var isPress = Input.IsPressed(Commands.Confirm, _parent.GetPlayerIndex());
                        if (!isPress)
                        {
                            _parent.SetInteraction(false);
                        }
                        if (isPress && !_parent.IsInteracting())
                        {
                            _parent.SetInteraction(true);
                        }
                        var skillCycleVelocity =
                            ((Input.IsPressed(Commands.CycleLeft, _parent.GetPlayerIndex())) ? -1 : 0)
                            +
                            ((Input.IsPressed(Commands.CycleRight, _parent.GetPlayerIndex())) ? 1 : 0);
                        _parent.CycleActiveSkill(skillCycleVelocity);

                        if (!_isCasting)
                        {
                            if (!Input.IsPressed(Commands.Confirm, _parent.GetPlayerIndex(),false))
                            {
                                _parent.MoveIfPossible(_keyVelocity.X, _keyVelocity.Y);
                            }
                            if (!_keyVelocity.IsZero())
                            {
                                _parent.SetSkillVector(_keyVelocity);
                            }
                        }
                    }
                    if (Input.IsPressed(Commands.Skill, _parent.GetPlayerIndex()))
                    {
                        _isCasting = true;
                    }

                    foreach (var hotkey in __hotkeys)
                    {
                        if (Input.IsPressed(hotkey,_parent.GetPlayerIndex()))
                        {
                            if (!Input.IsPressed(Commands.LockSkill, _parent.GetPlayerIndex(), false))
                            {
                                if (_parent.SetHotSkillActive(hotkey))
                                {
                                    _isCasting = true;
                                }
                            }
                            else
                            {
                                _parent.MarkHotSkill(hotkey);
                            }
                        }
                    }

                    if (_isCasting)
                    {
                        if (_parent.GetSkillVector() == null)
                        {
                            _parent.SetSkillVector(new Point2(1, 0));
                        }
                        if (!_parent.GetSkillVector().IsZero())
                        {
                            _parent.UseActiveSkill();
                            _isCasting = false;
                        }
                    }
                }
                
                if (Input.IsPressed(Commands.Inventory, _parent.GetPlayerIndex()))
                {
                    Input.SetContext(_parent.ToggleInventoryVisibility()? Contexts.Inventory: Contexts.Free, _parent.GetPlayerIndex());
                }
            }
        }
    }
}