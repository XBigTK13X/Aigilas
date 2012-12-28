package aigilas.creatures.impl;

import aigilas.Common;
import sps.bridge.ActorTypes;

public class GreedEnemyAcolyte extends BaseEnemy {
    public GreedEnemyAcolyte() {
        super(ActorTypes.get(Common.Actors.Greed_Acolyte));
    }
}
