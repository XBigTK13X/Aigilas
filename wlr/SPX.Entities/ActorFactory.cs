using System;
using SPX.Core;

namespace SPX.Entities
{
    public class IActorFactory
    {
        public virtual IActor Create(int actorType, Point2 position) { return null; }
        public virtual IActor GenerateActor(int actorType) { return null; }
        public virtual IActor CreateRandom(Point2 randomPoint) { return null; }
        public virtual void ResetPlayerCount() { }
        public virtual void IncreasePlayerCount() { }
        public virtual int GetPlayerCount() { return Int32.MinValue; }
    }
}