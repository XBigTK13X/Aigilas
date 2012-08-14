package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.spx.entities.*;import com.spx.core.*;
    public class Extensions
    {
        public static ICreature IsCreature(IEntity entity)
        {
            if (entity.GetEntityType() == com.aigilas.EntityType.ACTOR)
            {
                return (ICreature)entity;
            }
            return null;
        }
    }
