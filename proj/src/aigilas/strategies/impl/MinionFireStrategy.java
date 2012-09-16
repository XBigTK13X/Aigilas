package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class MinionFireStrategy extends IStrategy {
    public MinionFireStrategy(ICreature parent)

    {
        super(parent, Strategy.MinionFire);

        parent.setSkillVector(parent.getSkillVector());
    }

    @Override
    public void act() {
        if (_parent.isCooledDown()) {
            _parent.useActiveSkill();
            _parent.applyDamage(5, null, false);
            _parent.set(StatType.MOVE_COOL_DOWN, 0);
        }
    }
}
