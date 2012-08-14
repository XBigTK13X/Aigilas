package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.dungeons.*;import com.spx.core.*;import com.spx.entities.*;import com.aigilas.management.*;
    public class Downstairs  extends  Entity
    {
        public Downstairs(Point2 location)
        {
            Initialize(location, SpriteType.DOWNSTAIRS, com.aigilas.EntityType.DOWNSTAIRS,com.aigilas.Depth.Stairs);
        }

        private ICreature player;
@Override        public  void Update()
        {
            player = (ICreature)EntityManager.GetTouchingCreature(this);
            if (player != null)
            {
                if (player.IsInteracting())
                {
                    player.PerformInteraction();
                    try {						DungeonFactory.GetNextFloor();					} catch (Exception e) {						e.printStackTrace();					}
                }
            }
        }
    }
