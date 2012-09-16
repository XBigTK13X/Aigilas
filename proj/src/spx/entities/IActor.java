package spx.entities;

import spx.bridge.ActorType;

public interface IActor extends IEntity {
    ActorType getActorType();

    void performInteraction();
}
