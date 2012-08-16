package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.dungeons.*;import com.spx.entities.*;import com.aigilas.management.*;import com.spx.core.*;
    public class Upstairs  extends  Entity
    {
        public Upstairs(Point2 location)
        {
            Initialize(location, SpriteType.UPSTAIRS, com.aigilas.EntityType.UPSTAIRS,com.aigilas.Depth.Stairs);
        }

        ICreature player;
@Override        public  void  Update()
        {
            player = (Player)EntityManager.GetTouchingCreature(this);
            if(player!=null)
            {
                if (player.IsInteracting())
                {
                    player.PerformInteraction();
                    try {						DungeonFactory.GetPreviousFloor();					} catch (Exception e) {						e.printStackTrace();					}
                }    
            }
        }
    }
