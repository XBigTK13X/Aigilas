package aigilas.creatures.impl;

import aigilas.Common;
import sps.bridge.ActorTypes;

public class Pride extends BaseEnemy {
    public Pride() {
        super(ActorTypes.get(Common.Actors.Pride));
    }
}
