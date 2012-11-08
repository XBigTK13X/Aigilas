package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Peon extends BaseEnemy {
    public Peon() {
        super(ActorTypes.get(Common.Peon));
    }
}
