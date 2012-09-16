package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.skills.AnimationType;
import aigilas.skills.SkillLogic;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;
import spx.bridge.ActorType;

public class AttackStrategy extends IStrategy {
    private int _skillCooldown = 0;
    private int _skillCooldownMax = 10;

    public AttackStrategy(ICreature parent, ActorType... targetTypes)

    {
        super(parent, Strategy.Attack);

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
                if (SkillLogic.isSkill(_parent.getActiveSkill(), AnimationType.RANGED)) {
                    if (opponent != null) {
                        _parent.setSkillVector(CalculateTargetVector(_parent.getLocation(), opponent.getLocation()));
                    }
                    if (_parent.getSkillVector().GridX != 0 || _parent.getSkillVector().GridY != 0) {
                        _parent.useActiveSkill();
                    }
                } else {
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
