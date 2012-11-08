package aigilas.creatures.impl;

import aigilas.Common;
import sps.bridge.ActorTypes;

public class Peon extends BaseEnemy {
    public Peon() {
        super(ActorTypes.get(Common.Peon));
    }
}
