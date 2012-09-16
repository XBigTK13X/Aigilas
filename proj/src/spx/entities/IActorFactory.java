package spx.entities;

import spx.bridge.ActorType;
import spx.core.Point2;

public interface IActorFactory {
    IActor create(ActorType actorType, Point2 position);

    IActor GenerateActor(ActorType actorType);

    IActor createRandom(Point2 randomPoint);

    void ResetPlayerCount();

    void IncreasePlayerCount();

    int getPlayerCount();
}
