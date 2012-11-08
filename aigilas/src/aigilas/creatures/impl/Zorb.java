package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Zorb extends BaseEnemy {
    public Zorb() {
        super(ActorTypes.get(Common.Zorb));
    }
}
