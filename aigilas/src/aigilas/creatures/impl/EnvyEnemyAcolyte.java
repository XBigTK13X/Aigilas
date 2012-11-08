package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class EnvyEnemyAcolyte extends BaseEnemy {
    public EnvyEnemyAcolyte() {
        super(ActorTypes.get(Common.Envy_Acolyte));
    }
}
