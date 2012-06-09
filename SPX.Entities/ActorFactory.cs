using System;
using SPX.Core;

namespace SPX.Entities
{
    public interface IActorFactory
    {
        IActor Create(int actorType, Point2 position);

        IActor GenerateActor(int actorType);

        IActor CreateRandom(Point2 randomPoint);

        void ResetPlayerCount();

        void IncreasePlayerCount();

        int GetPlayerCount();
    }
}