package aigilas.creatures;

import aigilas.entities.Elements;
import spx.bridge.ActorType;

public class Peon extends AbstractCreature {
    public Peon() {
        super(ActorType.PEON);
        Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        Compose(Elements.EARTH);
    }
}
