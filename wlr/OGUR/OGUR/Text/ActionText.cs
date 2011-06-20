using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;

namespace OGUR.Text
{
    class ActionText:Text
    {

        protected int m_lifeSpan = 0;
        protected float m_scalePercent = 1;
        public ActionText(string contents, int lifeSpan, int x, int y)
            : base(contents, x, y,TextType.Action)
        {
            m_lifeSpan = lifeSpan;
        }
        public override int Update()
        {
            m_scalePercent *= .98f;
            return m_lifeSpan--;
        }
        public override void Draw()
        {
            SpriteBatch target = XnaManager.GetRenderTarget();
            target.Begin();
            // Find the center of the string
            Vector2 fontCenter = TextManager.GetFont().MeasureString(m_contents) / 2;
            target.DrawString(TextManager.GetFont(), m_contents, m_position, Color.Black, 0, fontCenter, 1.15f * m_scalePercent, SpriteEffects.None, 0.5f);
            target.DrawString(TextManager.GetFont(), m_contents, m_position, Color.White, 0, fontCenter, 1.0f * m_scalePercent, SpriteEffects.None, 0.5f);
            target.End();
        }
    }
}
