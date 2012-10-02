package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class PrideEnemyAcolyte extends BaseEnemy {
    public PrideEnemyAcolyte() {
        super(ActorType.PRIDE_ACOLYTE);
        Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        Compose(Elements.DARK);
    }
}
