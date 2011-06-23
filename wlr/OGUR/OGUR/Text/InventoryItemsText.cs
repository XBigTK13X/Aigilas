using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Management;
using OGUR.Storage;

namespace OGUR.Text
{
    class InventoryItemsText:Text
    {
        private ICreature m_player;
        public InventoryItemsText(string contents, int x, int y, ICreature player)
            : base(contents, x, y,TextType.Inventory)
        {
            m_player = player;
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
            target.DrawString(TextManager.GetFont(), m_contents, m_position+m_player.GetHudOrigin(), Color.White, 0, fontCenter,.5f, SpriteEffects.None, 0.5f);
            target.End();
        }
    }
}
