using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;
using OGUR.Storage;

namespace OGUR.Text
{
    class InventoryItemsText:Text
    {
        private int m_playerIndex;
        public InventoryItemsText(string contents, int x, int y, int playerIndex)
            : base(contents, x, y,TextType.Inventory)
        {
            m_playerIndex = playerIndex;
        }
        public override int Update()
        {
           return 1;
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
