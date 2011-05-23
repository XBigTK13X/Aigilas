using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.GameObjects;
using welikerogues.Sprites;

namespace welikerogues.Collision
{
    static class HitTest
    {
        static public bool IsTouching(GameplayObject source, GameplayObject target)
        {
            if (IsClose(source, target))
            {
                return true;
            }
            return false;
        }
        static private bool IsClose(GameplayObject source, GameplayObject target)
        {
            return IsClose(source.GetPosition().X, target.GetPosition().X, source.GetPosition().Y, target.GetPosition().Y);
        }
        static private bool IsClose(float x1, float x2, float y1, float y2)
        {
            return (Math.Pow((x1 - x2), 2) + Math.Pow((y1 - y2), 2)) <= 2*(SpriteInfo.Radius*SpriteInfo.Radius);
        }
    }
}
