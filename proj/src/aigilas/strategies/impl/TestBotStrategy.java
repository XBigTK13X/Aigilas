package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.SkillLogic;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import spx.bridge.ActorType;
import spx.bridge.EntityType;
import spx.core.RNG;
import spx.entities.EntityManager;
import spx.entities.HitTest;
import spx.entities.IEntity;
import spx.paths.PathFinder;

public class TestBotStrategy extends BaseStrategy {
    public TestBotStrategy(BaseCreature parent) {
        super(parent, Strategy.TestBot);

        _targets.addTargetTypes(ActorType.NONPLAYER);
    }

    private IEntity _stairsTarget;

    @Override
    public void act() {
        if (RNG.next(0, 1000) == 2) {
            _parent.cycleActiveSkill(1);
        }
        if (RNG.next(0, 100) <= 1) {
            _parent.useActiveSkill();
        }
        if (AbleToMove()) {
            if (SkillLogic.isSkill(_parent.getActiveSkill(), AnimationType.RANGED)) {
                if (opponent != null) {
                    _parent.setSkillVector(CalculateTargetVector(_parent.getLocation(), opponent.getLocation()));
                }
                if (_parent.getSkillVector().GridX != 0 || _parent.getSkillVector().GridY != 0) {
                    _parent.useActiveSkill();
                }
            }
            if (targetPath.hasMoves()) {
                nextMove.copy(targetPath.getNextMove());
                _parent.moveTo(nextMove);
                if (_stairsTarget != null && HitTest.isTouching(_parent, _stairsTarget)) {
                    _parent.setInteracting(true);
                } else {
                    _parent.setInteracting(false);
                }
            }
        } else {
            if ((targetPath == null || !targetPath.hasMoves()) && EntityManager.getObjects(EntityType.ACTOR).size() == 1) {
                _stairsTarget = EntityManager.getObject(EntityType.DOWNSTAIRS);
                if (_stairsTarget != null) {
                    targetPath.copy(PathFinder.findNextMove(_parent.getLocation(), _stairsTarget.getLocation()));
                }
            }
        }
    }
}
