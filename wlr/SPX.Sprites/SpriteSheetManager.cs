using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace SPX.Sprites
{
    public class SpriteDefinition
    {
        public SpriteDefinition(int type, int index, int frames)
        {
            Type = type;
            Info = new SpriteInfo(index, frames);
        }
        public int Type { get; set; }
        public SpriteInfo Info { get; set; }
    }
    public interface ISpriteInitializer
    {
        ICollection<SpriteDefinition> GetSprites();
    }
    public class SpriteSheetManager
    {
        private static Dictionary<int, SpriteInfo> __manager = new Dictionary<int, SpriteInfo>();

        public static SpriteInfo GetSpriteInfo(int spriteName)
        {
            return __manager[spriteName];
        }

        public static void Setup(ISpriteInitializer initializer)
        {
            foreach (var sprite in initializer.GetSprites())
            {
                __manager.Add(sprite.Type, sprite.Info);
            }
        }
    }
}