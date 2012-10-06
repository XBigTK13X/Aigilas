package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.Stats;
import aigilas.management.Commands;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.ActorType;
import sps.core.Point2;
import sps.io.Contexts;
import sps.io.Input;

import java.util.Arrays;
import java.util.List;

public class ControlledByPlayer extends BaseStrategy {
    private boolean _isCasting = false;
    private final Point2 _keyVelocity = new Point2(0, 0);

    private static final List<Commands> __hotkeys = Arrays.asList(Commands.HotSkill1, Commands.HotSkill2, Commands.HotSkill3);

    public ControlledByPlayer(BaseCreature parent) {
        super(parent, Strategy.ControlledByPlayer);

        _targets.addTargetTypes(ActorType.NONPLAYER);
    }

    @Override
    public void act() {
        if (Input.isActive(Commands.Start, _parent.getPlayerIndex())) {
            _parent.setPlaying(true);
        }
        if (Input.isActive(Commands.Back, _parent.getPlayerIndex())) {
            _parent.setPlaying(false);
        }
        if (_parent.isPlaying()) {
            if (!Input.isContext(Contexts.Inventory, _parent.getPlayerIndex())) {
                float leftVelocity = (Input.isActive(Commands.MoveLeft, _parent.getPlayerIndex()) ? -Stats.DefaultMoveDistance : 0);
                float rightVelocity = ((Input.isActive(Commands.MoveRight, _parent.getPlayerIndex())) ? Stats.DefaultMoveDistance : 0);
                _keyVelocity.setX(rightVelocity + leftVelocity);

                float downVelocity = ((Input.isActive(Commands.MoveDown, _parent.getPlayerIndex())) ? -Stats.DefaultMoveDistance : 0);
                float upVelocity = ((Input.isActive(Commands.MoveUp, _parent.getPlayerIndex())) ? Stats.DefaultMoveDistance : 0);
                _keyVelocity.setY(upVelocity + downVelocity);

                if (Input.isContext(Contexts.Free, _parent.getPlayerIndex())) {
                    boolean isPress = Input.isActive(Commands.Confirm, _parent.getPlayerIndex());
                    if (!isPress) {
                        _parent.setInteraction(false);
                    }
                    if (isPress && !_parent.isInteracting()) {
                        _parent.setInteraction(true);
                    }
                    int skillCycleVelocity = ((Input.isActive(Commands.CycleLeft, _parent.getPlayerIndex())) ? -1 : 0) + ((Input.isActive(Commands.CycleRight, _parent.getPlayerIndex())) ? 1 : 0);
                    _parent.cycleActiveSkill(skillCycleVelocity);

                    if (!_isCasting) {
                        if (!Input.isActive(Commands.Confirm, _parent.getPlayerIndex(), false)) {
                            _parent.moveIfPossible(_keyVelocity.X, _keyVelocity.Y);
                        }
                        if (!_keyVelocity.isZero()) {
                            _parent.setSkillVector(_keyVelocity);
                        }
                    }
                }
                if (Input.isActive(Commands.Skill, _parent.getPlayerIndex())) {
                    _isCasting = true;
                }

                for (Commands hotkey : __hotkeys) {
                    if (Input.isActive(hotkey, _parent.getPlayerIndex())) {
                        if (!Input.isActive(Commands.LockSkill, _parent.getPlayerIndex(), false)) {
                            if (_parent.setHotSkillActive(hotkey)) {
                                _isCasting = true;
                            }
                        }
                        else {
                            _parent.markHotSkill(hotkey);
                        }
                    }
                }

                if (_isCasting) {
                    if (_parent.getSkillVector() == null) {
                        _parent.setSkillVector(new Point2(1, 0));
                    }
                    if (!_parent.getSkillVector().isZero()) {
                        _parent.useActiveSkill();
                    }
                    _isCasting = false;
                }
            }

            if (Input.isActive(Commands.Inventory, _parent.getPlayerIndex())) {
                Input.setContext(_parent.toggleInventoryVisibility() ? Contexts.Inventory : Contexts.Free, _parent.getPlayerIndex());
            }
        }
    }
}
