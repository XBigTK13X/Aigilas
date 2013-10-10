package aigilas.creatures.impl;

import aigilas.Aigilas;
import sps.bridge.ActorTypes;

public class GreedEnemyAcolyte extends BaseEnemy {
    public GreedEnemyAcolyte() {
        super(ActorTypes.get(Aigilas.Actors.Greed_Acolyte));
    }
}
