package aigilas.creatures.impl;

import aigilas.Aigilas;
import sps.bridge.ActorTypes;

public class SlothEnemyAcolyte extends BaseEnemy {
    public SlothEnemyAcolyte() {
        super(ActorTypes.get(Aigilas.Actors.Sloth_Acolyte));
    }
}
