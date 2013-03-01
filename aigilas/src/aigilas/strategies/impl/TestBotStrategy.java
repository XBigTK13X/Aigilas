package aigilas.strategies.impl;

import aigilas.Aigilas;
import aigilas.creatures.BaseCreature;
import aigilas.skills.AnimationType;
import aigilas.skills.SkillLogic;
import aigilas.strategies.BaseStrategy;
import aigilas.strategies.Strategy;
import sps.bridge.ActorTypes;
import sps.bridge.EntityTypes;
import sps.bridge.Sps;
import sps.core.RNG;
import sps.entities.Entity;
import sps.entities.EntityManager;
import sps.entities.HitTest;
import sps.paths.PathFinder;

public class TestBotStrategy extends BaseStrategy {
    public TestBotStrategy(BaseCreature parent) {
        super(parent, Strategy.TestBot);

        _targets.addTargetTypes(new sps.bridge.ActorType[]{ActorTypes.get(Sps.ActorGroups.Non_Player)});
    }

    private Entity _stairsTarget;

    @Override
    public void act() {
        if (RNG.next(0, 1000) == 2) {
            _parent.cycleActiveSkill(1);
        }
        if (RNG.next(0, 100) <= 1) {
            _parent.useActiveSkill();
        }
        if (AbleToMove()) {
            if (SkillLogic.get().isSkill(_parent.getActiveSkill(), AnimationType.RANGED)) {
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
                }
                else {
                    _parent.setInteracting(false);
                }
            }
        }
        else {
            if ((((targetPath == null || !targetPath.hasMoves()) && EntityManager.get().getEntities(EntityTypes.get(Sps.Entities.Actor)).size() == 1))) {
                _stairsTarget = EntityManager.get().getEntity(EntityTypes.get(Aigilas.Entities.Downstairs));
                if (_stairsTarget != null) {
                    targetPath = PathFinder.find(_parent.getLocation(), _stairsTarget.getLocation());
                }
            }
        }
    }
}
