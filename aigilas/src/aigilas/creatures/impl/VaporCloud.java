package aigilas.creatures.impl;

import aigilas.creatures.BaseCreature;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;
import sps.entities.EntityManager;
import sps.entities.IActor;

public class VaporCloud extends Minion {
    private BaseCreature _host = null;

    public VaporCloud() {
        super(ActorType.MINION);
        _strategy = StrategyFactory.create(Strategy.MinionCloud, this);
        add(SkillId.VAPOR_CLOUD);
        _composition.add(Elements.WATER);
    }

    @Override
    public void update() {
        super.update();
        if (_host == null) {
            for (IActor creature : EntityManager.get().getActorsAt(_location)) {
                if (creature != this) {
                    _host = (BaseCreature) creature;
                }
            }
            if (_host == null) {
                setInactive();
            }
        }
        if (_host != null) {
            setLocation(_host.getLocation());
        }
    }
}
