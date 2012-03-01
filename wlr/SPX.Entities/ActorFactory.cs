using System;
using SPX.Core;

namespace SPX.Entities
{
    public interface IActorFactory
    {
        IActor Create(int creatureType, Point2 position);

        IActor GenerateActor(int creatureType);

        IActor CreateRandom(Point2 randomPoint);

        void ResetPlayerCount();

        void IncreasePlayerCount();

        int GetPlayerCount();
    }
}