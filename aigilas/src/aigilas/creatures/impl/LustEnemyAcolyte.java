package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class LustEnemyAcolyte extends BaseEnemy {
    public LustEnemyAcolyte() {
        super(ActorType.LUST_ACOLYTE);
        Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        Compose(Elements.FIRE);
    }
}
