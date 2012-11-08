package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Envy extends BaseEnemy {
    public Envy() {
        super(ActorTypes.get(Common.Envy));
    }
}
