package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class GluttonyEnemyAcolyte extends BaseEnemy {
    public GluttonyEnemyAcolyte() {
        super(ActorTypes.get(Common.Gluttony_Acolyte));
    }
}
