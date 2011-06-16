using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;

namespace OGUR.Text
{
    class Text
    {
        private string m_contents = "";
        private int m_lifeSpan = 0;
        private Vector2 m_position;
        public Text(string contents,int lifeSpan,int x, int y)
        {
            m_contents = contents;
            m_lifeSpan = lifeSpan;
            m_position = new Vector2(x,y);
        }
        public int Update()
        {
            return m_lifeSpan--;
        }

        public void Draw()
        {
            SpriteBatch target = XnaManager.GetRenderTarget();
            target.Begin();

            // Find the center of the string
            Vector2 fontCenter = TextManager.GetActionFont().MeasureString(m_contents) / 2;
            target.DrawString(TextManager.GetActionFont(),m_contents, m_position, Color.LightGreen,0, fontCenter, 1.0f, SpriteEffects.None, 0.5f);
            target.End();
        }
    }
}
