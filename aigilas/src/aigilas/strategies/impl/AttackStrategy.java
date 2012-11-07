package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.SkillLogic;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;

public class AttackStrategy extends BaseStrategy {
    private int _skillCooldown = 0;
    private final int _skillCooldownMax = 10;

    public AttackStrategy(BaseCreature parent, ActorType... targetTypes) {
        super(parent, Strategy.Attack);

        if (targetTypes.length == 1) {
            if (targetTypes[0] == ActorTypes.get("Friendly")) {
                if (parent.getActorType() != ActorTypes.get("Player")) {
                    targetTypes[0] = ActorTypes.get("Non_Player");
                }
                else {
                    targetTypes[0] = ActorTypes.get("Player");
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
                _parent.cycleActiveSkill(1);
                if (SkillLogic.get().isSkill(_parent.getActiveSkill(), AnimationType.RANGED)) {
                    if (opponent != null) {
                        _parent.setSkillVector(CalculateTargetVector(_parent.getLocation(), opponent.getLocation()));
                    }
                    if (_parent.getSkillVector().GridX != 0 || _parent.getSkillVector().GridY != 0) {
                        _parent.useActiveSkill();
                    }
                }
                else {
                    _parent.useActiveSkill();
                }
                _skillCooldown = _skillCooldownMax;
            }
            if (targetPath.hasMoves()) {
                nextMove.copy(targetPath.getNextMove());
                _parent.moveTo(nextMove);
            }
        }
    }
}
