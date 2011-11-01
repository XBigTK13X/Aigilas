using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Management;
using OGUR.Storage;

namespace OGUR.Text
{
    class DefaultHudText:Text
    {
        private readonly ICreature m_player;
        private readonly Color m_color;

        public DefaultHudText(string contents, int x, int y, ICreature player,float alpha=1f)
            : base(contents, x, y,TextType.Inventory)
        {
            m_color = new Color(255f,255f,255f,alpha);
            m_player = player;
        }
        public override int Update()
        {
           return 1;
        }
        public override void Draw()
        {
            var fontCenter = new Vector2(0, 0);
            XnaManager.Renderer.DrawString(TextManager.GetFont(), m_contents, m_position+m_player.GetHudOrigin(), m_color, 0, fontCenter,.75f, SpriteEffects.None, .96f);
        }
    }
}
