using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.HUD;
using OGUR.Management;

namespace OGUR.Text
{
    class InventoryItemsText:Text
    {
        private int m_playerIndex;
        public InventoryItemsText(string contents, int x, int y, int playerIndex)
            : base(contents, x, y)
        {
            m_playerIndex = playerIndex;
        }
        public override int Update()
        {
            if(InventoryScreensManager.IsShowing(m_playerIndex))
            {
                return 1;
            }
            return 0;
        }
        public override void Draw()
        {
            SpriteBatch target = XnaManager.GetRenderTarget();
            target.Begin();
            Vector2 fontCenter = new Vector2(0, 0);// TextManager.GetFont().MeasureString(m_contents) / 2;
            target.DrawString(TextManager.GetFont(), m_contents, m_position, Color.White, 0, fontCenter, 1, SpriteEffects.None, 0.5f);
            target.End();
        }
    }
}
