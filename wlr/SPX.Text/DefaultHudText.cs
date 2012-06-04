using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using SPX.Core;
using System;

namespace SPX.Text
{
    [Serializable]
    class DefaultHudText:Text
    {
        private Color _color;
        private Vector2 _origin;

        public DefaultHudText(float alpha=1f)
        {
            _color = new Color(255f, 255f, 255f, alpha);
        }

        public DefaultHudText(string contents, int x, int y,Vector2 origin,float alpha=1f)
            : base(contents, x, y,TextType.Inventory)
        {
            _color = new Color(255f,255f,255f,alpha);
            _origin = origin;
        }

        public void Reset(string contents, int x, int y,Vector2 origin)
        {
            _origin = origin;
            Reset(contents, x, y);
        }

        public override int Update()
        {
           return 1;
        }
        public override void Draw()
        {
            XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position+_origin, _color, 0, Vector2.Zero,.75f, SpriteEffects.None, Depth.DefaultHudText);
        }
    }
}
