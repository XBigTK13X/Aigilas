package com.spx.entities;import java.util.*;import com.spx.core.*;import com.xna.wrapper.*;
    public interface IActor extends IEntity
    {
        int GetActorType();
        void PerformInteraction();
    }
