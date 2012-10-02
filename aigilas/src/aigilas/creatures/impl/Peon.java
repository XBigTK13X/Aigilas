package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class Peon extends BaseEnemy {
    public Peon() {
        super(ActorType.PEON);
        Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        Compose(Elements.EARTH);
    }
}
