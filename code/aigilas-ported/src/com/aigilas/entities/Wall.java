package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.spx.core.*;import com.aigilas.management.*;import com.spx.entities.*;
    public class Wall  extends  Entity
    {
        public Wall(Point2 location)
        {
            Initialize(location, SpriteType.WALL, com.aigilas.EntityType.WALL,com.aigilas.Depth.Wall);
            _isBlocking = true;
        }
    }
