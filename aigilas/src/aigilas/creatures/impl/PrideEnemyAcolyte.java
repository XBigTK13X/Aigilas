package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class PrideEnemyAcolyte extends BaseEnemy {
    public PrideEnemyAcolyte() {
        super(ActorTypes.get(Common.Pride_Acolyte));
    }
}
