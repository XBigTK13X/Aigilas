package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Pride extends BaseEnemy {
    public Pride() {
        super(ActorTypes.get(Common.Pride));
    }
}
