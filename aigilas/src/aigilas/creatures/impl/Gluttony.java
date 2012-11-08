package aigilas.creatures.impl;

import aigilas.management.Common;
import sps.bridge.ActorTypes;

public class Gluttony extends BaseEnemy {
    public Gluttony() {
        super(ActorTypes.get(Common.Gluttony));
    }
}
