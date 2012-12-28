package aigilas.creatures.impl;

import aigilas.Common;
import sps.bridge.ActorTypes;

public class Greed extends BaseEnemy {
    public Greed() {
        super(ActorTypes.get(Common.Actors.Greed));
    }
}
