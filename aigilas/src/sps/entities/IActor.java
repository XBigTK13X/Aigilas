package sps.entities;

import sps.bridge.ActorType;

public interface IActor extends IEntity {
    ActorType getActorType();
    void performInteraction();
}
