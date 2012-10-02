package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class GreedEnemyAcolyte extends BaseEnemy {
    public GreedEnemyAcolyte() {
        super(ActorType.GREED_ACOLYTE);
        Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        Compose(Elements.AIR);
    }
}
