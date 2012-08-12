package com.spx.entities;import com.spx.wrapper.*;import java.util.*;import com.spx.core.*;
    public interface IActor extends IEntity
    {
        int GetActorType();
        void PerformInteraction();
    }
