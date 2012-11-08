package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Wrath extends BaseEnemy {
    public Wrath() {
        super(ActorTypes.get(Common.Wrath));
    }
}
