package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class WrathEnemyAcolyte extends BaseEnemy {
    public WrathEnemyAcolyte() {
        super(ActorTypes.get(Common.Wrath_Acolyte));
    }
}
