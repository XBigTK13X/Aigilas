package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class WrathEnemyAcolyte extends BaseEnemy {
    public WrathEnemyAcolyte() {
        super(ActorType.WRATH_ACOLYTE);
        Weaknesses(StatType.STRENGTH, StatType.HEALTH, StatType.MOVE_COOL_DOWN);
        Compose(Elements.PHYSICAL);
    }
}
