package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.SkillLogic;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;
import sps.bridge.Sps;
import sps.core.RNG;

public class AttackStrategy extends BaseStrategy {
    private int _skillCooldown = 0;
    private final int _skillCooldownMax = 10;

    public AttackStrategy(BaseCreature parent, ActorType... targetTypes) {
        super(parent, Strategy.Attack);

        if (targetTypes.length == 1) {
            if (targetTypes[0] == ActorTypes.get(Sps.ActorGroups.Friendly)) {
                if (ActorTypes.get(Sps.Actors.Player) != parent.getActorType()) {
                    targetTypes[0] = ActorTypes.get(Sps.ActorGroups.Non_Player);
                }
                else {
                    targetTypes[0] = ActorTypes.get(Sps.Actors.Player);
                }
            }
        }

        for (ActorType targetType : targetTypes) {
            _targets.addTargetTypes(targetType);
        }
    }

    @Override
    public void act() {
        if (AbleToMove()) {
            _skillCooldown--;
            if (_skillCooldown <= 0) {
                if (RNG.percent(50)) {
                    if (RNG.coinFlip()) {
                        _parent.cycleActiveSkill(1);
                    }
                    if (SkillLogic.get().isSkill(_parent.getActiveSkill(), AnimationType.RANGED)) {
                        if (opponent != null) {
                            _parent.setSkillVector(CalculateTargetVector(_parent.getLocation(), opponent.getLocation()));
                        }
                        if (_parent.getSkillVector().X != 0 || _parent.getSkillVector().Y != 0) {
                            _parent.useActiveSkill();
                        }
                    }
                    else {
                        _parent.useActiveSkill();
                    }
                    _skillCooldown = _skillCooldownMax;
                }
            }
            if (targetPath.hasMoves()) {
                nextMove.copy(targetPath.getNextMove());
                _parent.moveTo(nextMove);
            }
        }
    }
}
