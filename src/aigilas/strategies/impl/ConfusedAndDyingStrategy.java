package aigilas.strategies.impl;

import aigilas.creatures.BaseCreature;

public class ConfusedAndDyingStrategy extends ConfusedStrategy {
    public ConfusedAndDyingStrategy(BaseCreature parent) {
        super(parent);

    }

    @Override
    public void act() {
        super.act();
        _parent.applyDamage(2, null, false);
    }
}
