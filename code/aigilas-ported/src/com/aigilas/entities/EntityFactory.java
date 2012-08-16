package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.spx.entities.*;import com.spx.core.*;
    public class EntityFactory
    {
        public static Entity Create(int type, Point2 location)
        {
            switch (type)
            {
                case com.aigilas.EntityType.FLOOR:
                    return new Floor(location);
                case com.aigilas.EntityType.WALL:
                    return new Wall(location);
                case com.aigilas.EntityType.DOWNSTAIRS:
                    return new Downstairs(location);
                case com.aigilas.EntityType.UPSTAIRS:
                    return new Upstairs(location);
                default:
				try {					throw new Exception("An undefined int case was passed into the EntityFactory.");				} catch (Exception e) {					// TODO Auto-generated catch block					e.printStackTrace();				}				finally{					return null;				}
            }
        }
    }
