package com.spx.entities;import com.spx.wrapper.*;import java.util.*;import com.spx.core.*;
    public class HitTest
    {
        public static boolean IsTouching(IEntity source, IEntity target)
        {
            if (IsClose(source, target))
            {
                return true;
            }
            return false;
        }

        private static boolean IsClose(IEntity source, IEntity target)
        {
            return IsClose(source.GetLocation().PosX, target.GetLocation().PosX, source.GetLocation().PosY,
                           target.GetLocation().PosY);
        }

        private static boolean IsClose(float x1, float x2, float y1, float y2)
        {
            return GetDistanceSquare(x1,x2,y1,y2) < GameManager.SpriteRadius;
        }

        public static float GetDistanceSquare(IEntity source, IEntity target)
        {
            return GetDistanceSquare(source.GetLocation().PosX, target.GetLocation().PosX, source.GetLocation().PosY,
                                     target.GetLocation().PosY);
        }
        public static float GetDistanceSquare(Point2 source, Point2 target)
        {
            return GetDistanceSquare(source.PosX, target.PosX, source.PosY, target.PosY);
        }
        private static float GetDistanceSquare(float x1,float x2, float y1, float y2)
        {
            return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        }
    }
