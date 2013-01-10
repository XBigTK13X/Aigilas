package sps.entities;

import sps.bridge.ActorType;

public interface IActor {
    ActorType getActorType();

    void performInteraction();
}
