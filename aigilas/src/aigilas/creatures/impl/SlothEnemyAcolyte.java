package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class SlothEnemyAcolyte extends BaseEnemy {
    public SlothEnemyAcolyte() {
        super(ActorTypes.get(Common.Sloth_Acolyte));
    }
}
