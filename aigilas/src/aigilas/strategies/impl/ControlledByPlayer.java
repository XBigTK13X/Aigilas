package aigilas.strategies.impl;

import aigilas.Aigilas;
import aigilas.AigilasConfig;
import aigilas.creatures.BaseCreature;
import aigilas.creatures.Stats;
import aigilas.creatures.impl.Player;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.*;
import sps.core.Point2;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.io.Input;

import java.util.Arrays;
import java.util.List;

public class ControlledByPlayer extends BaseStrategy {
    private boolean _isCasting = false;
    private final Point2 _keyVelocity = new Point2(0, 0);

    private static final List<Command> __hotkeys = Arrays.asList(Commands.get(Aigilas.Commands.Hot_Skill_1), Commands.get(Aigilas.Commands.Hot_Skill_2), Commands.get(Aigilas.Commands.Hot_Skill_3));

    public ControlledByPlayer(BaseCreature parent) {
        super(parent, Strategy.ControlledByPlayer);

        _targets.addTargetTypes(new sps.bridge.ActorType[]{ActorTypes.get(Sps.ActorGroups.Non_Player)});
    }

    @Override
    public void act() {
        if (Input.get().isActive(Commands.get(Aigilas.Commands.Start), _parent.getPlayerIndex())) {
            _parent.setPlaying(true);
        }
        if (Input.get().isActive(Commands.get(Aigilas.Commands.Back), _parent.getPlayerIndex())) {
            _parent.setPlaying(false);
        }
        if (_parent.isPlaying()) {
            if (!Input.get().isContext(Contexts.get(Aigilas.Commands.Inventory), _parent.getPlayerIndex())) {
                float leftVelocity = (Input.get().isActive(Commands.get(Aigilas.Commands.MoveLeft), _parent.getPlayerIndex()) ? -Stats.DefaultMoveDistance : 0);
                float rightVelocity = ((Input.get().isActive(Commands.get(Aigilas.Commands.MoveRight), _parent.getPlayerIndex())) ? Stats.DefaultMoveDistance : 0);
                _keyVelocity.setX(rightVelocity + leftVelocity);
                if (_keyVelocity.X > 0) {
                    _parent.setFacingLeft(false);
                }
                if (_keyVelocity.X < 0) {
                    _parent.setFacingLeft(true);
                }
                float downVelocity = ((Input.get().isActive(Commands.get(Aigilas.Commands.MoveDown), _parent.getPlayerIndex())) ? -Stats.DefaultMoveDistance : 0);
                float upVelocity = ((Input.get().isActive(Commands.get(Aigilas.Commands.MoveUp), _parent.getPlayerIndex())) ? Stats.DefaultMoveDistance : 0);
                _keyVelocity.setY(upVelocity + downVelocity);

                if (Input.get().isContext(Contexts.get(Sps.Contexts.Free), _parent.getPlayerIndex())) {
                    boolean isPress = Input.get().isActive(Commands.get(Aigilas.Commands.Confirm), _parent.getPlayerIndex());
                    if (!isPress) {
                        _parent.setInteraction(false);
                    }
                    if (isPress && !_parent.isInteracting()) {
                        _parent.setInteraction(true);
                    }
                    int skillCycleVelocity = ((Input.get().isActive(Commands.get(Aigilas.Commands.CycleLeft), _parent.getPlayerIndex())) ? -1 : 0) + ((Input.get().isActive(Commands.get(Aigilas.Commands.CycleRight), _parent.getPlayerIndex())) ? 1 : 0);
                    _parent.cycleActiveSkill(skillCycleVelocity);

                    if (!_isCasting) {
                        if (!Input.get().isActive(Commands.get(Aigilas.Commands.Confirm), _parent.getPlayerIndex(), false)) {
                            _parent.moveIfPossible(_keyVelocity.X, _keyVelocity.Y);
                        }
                        if (!_keyVelocity.isZero()) {
                            _parent.setSkillVector(_keyVelocity);
                        }
                    }
                }
                if (Input.get().isActive(Commands.get(Aigilas.Commands.Skill), _parent.getPlayerIndex())) {
                    _isCasting = true;
                }

                for (Command hotkey : __hotkeys) {
                    if (Input.get().isActive(hotkey, _parent.getPlayerIndex())) {
                        if (!Input.get().isActive(Commands.get(Aigilas.Commands.LockSkill), _parent.getPlayerIndex(), false)) {
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

            if (Input.get().isActive(Commands.get(Aigilas.Commands.Inventory), _parent.getPlayerIndex())) {
                if (AigilasConfig.get().debugInventory) {
                    for (Entity player : EntityManager.get().getPlayers()) {
                        Player p = (Player) player;
                        if (p.getPlayerIndex() != _parent.getPlayerIndex()) {
                            p.toggleInventoryVisibility();
                        }
                    }
                }
                Input.get().setContext(_parent.toggleInventoryVisibility() ? Contexts.get(Aigilas.Commands.Inventory) : Contexts.get(Sps.Contexts.Free), _parent.getPlayerIndex());
            }
        }
    }
}
