using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;
using System.Runtime.Serialization;

namespace SPX.Sprites
{
    [Serializable()]
    public class SpriteInfo:ISerializable
    {
        public int X, Y, SpriteIndex, MaxFrame;

        public SpriteInfo(int spriteIndex, int maxFrame)
        {
            X = GameManager.SpriteWidth;
            Y = GameManager.SpriteHeight;
            SpriteIndex = spriteIndex;
            MaxFrame = maxFrame;
        }

        public SpriteInfo(SerializationInfo info, StreamingContext context)
        {
            SpriteIndex = (int)info.GetValue("SpriteInfo.Index", typeof(int));
            MaxFrame = (int)info.GetValue("SpriteInfo.MaxFrame", typeof(int));
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue("SpriteInfo.Index", SpriteIndex);
            info.AddValue("SpriteInfo.MaxFrame", MaxFrame);
        }
    }
}