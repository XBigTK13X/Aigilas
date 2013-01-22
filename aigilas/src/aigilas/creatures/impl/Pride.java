package aigilas.creatures.impl;

import aigilas.Aigilas;
import sps.bridge.ActorTypes;

public class Pride extends BaseEnemy {
    public Pride() {
        super(ActorTypes.get(Aigilas.Actors.Pride));
    }
}
