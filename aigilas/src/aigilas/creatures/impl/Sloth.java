package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Sloth extends BaseEnemy {
    public Sloth() {
        super(ActorTypes.get(Common.Sloth));
    }
}
