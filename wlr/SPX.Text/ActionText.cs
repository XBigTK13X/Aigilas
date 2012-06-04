using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using SPX.Core;

namespace SPX.Text
{
    [Serializable]
    public class ActionText:Text
    {

        protected int _lifeSpan = 0;
        protected float _scalePercent = 1;
        public ActionText(string contents, int lifeSpan, int x, int y)
            : base(contents, x, y,TextType.Action)
        {
            _lifeSpan = lifeSpan;
        }
        public ActionText()
        {

        }
        public void Reset(string contents, int lifespan, int x, int y)
        {
            _scalePercent = 1;
            _lifeSpan = lifespan;
            Reset(contents, x, y);
        }
        public override int Update()
        {
            _scalePercent *= .98f;
            return _lifeSpan--;
        }
        public override void Draw()
        {
            var fontCenter = TextManager.GetFont().MeasureString(_contents) / 2;
            XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position, Color.Black, 0, fontCenter, 1.15f * _scalePercent, SpriteEffects.None, Depth.ActionTextBG);
            XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position, Color.White, 0, fontCenter, 1.0f * _scalePercent, SpriteEffects.None, Depth.ActionText);
        }
    }
}
