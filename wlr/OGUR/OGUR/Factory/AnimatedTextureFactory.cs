using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Content;
using OGUR.Sprites;

namespace OGUR.Factory
{
    internal class AnimatedTextureFactory
    {
        public static AnimatedTexture Create(SpriteType type, int x, int y)
        {
            var sprite = new AnimatedTexture();
            sprite.LoadContent(type);
            sprite.SetPosition(x, y);
            return sprite;
        }
    }
}