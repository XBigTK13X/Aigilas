package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import spx.bridge.ActorType;
import spx.entities.EntityManager;
import spx.entities.IActor;

public class VaporCloud extends Minion {
    private ICreature _host = null;

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
            for (IActor creature : EntityManager.getActorsAt(_location)) {
                if (creature != this) {
                    _host = (ICreature) creature;
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
