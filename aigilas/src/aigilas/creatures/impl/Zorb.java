package aigilas.creatures.impl;

import aigilas.Common;
import sps.bridge.ActorTypes;

public class Zorb extends BaseEnemy {
    public Zorb() {
        super(ActorTypes.get(Common.Zorb));
    }
}
