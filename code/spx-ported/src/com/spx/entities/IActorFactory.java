package com.spx.entities;import java.util.*;import com.spx.core.*;import com.xna.wrapper.*;
    public interface IActorFactory
    {
        IActor Create(int actorType, Point2 position);

        IActor GenerateActor(int actorType);

        IActor CreateRandom(Point2 randomPoint);

        void ResetPlayerCount();

        void IncreasePlayerCount();

        int GetPlayerCount();
    }
