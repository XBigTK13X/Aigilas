package com.spx.text;import java.util.*;import com.spx.core.*;import com.xna.wrapper.*;
    class DefaultHudText extends Text
    {
        private Color _color;
        private Vector2 _origin;

        public DefaultHudText(float alpha)
        {
            _color = new Color(255f, 255f, 255f, alpha);
        }

        public DefaultHudText(String contents, int x, int y,Vector2 origin,float alpha)
            {        	super(contents, x, y,TextType.Inventory);            _color = new Color(255f,255f,255f,alpha);
            _origin = origin;
        }

        public void Reset(String contents, int x, int y,Vector2 origin)
        {
            _origin = origin;
            Reset(contents, x, y);
        }
        @Override
        public int Update()
        {
           return 1;
        }                @Override
        public void Draw()
        {
            XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position.add(_origin), _color, 0, Vector2.Zero,.75f, SpriteEffects.None, Depth.DefaultHudText);
        }
    }
