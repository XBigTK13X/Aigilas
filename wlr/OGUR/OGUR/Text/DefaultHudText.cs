using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Management;

namespace OGUR.Text
{
    class DefaultHudText:Text
    {
        private Color m_color;
        private Vector2 m_origin;

        public DefaultHudText(float alpha=1f)
        {
            m_color = new Color(255f, 255f, 255f, alpha);
        }

        public DefaultHudText(string contents, int x, int y,Vector2 origin,float alpha=1f)
            : base(contents, x, y,TextType.Inventory)
        {
            m_color = new Color(255f,255f,255f,alpha);
            m_origin = origin;
        }

        public void Reset(string contents, int x, int y,Vector2 origin)
        {
            m_origin = origin;
            Reset(contents, x, y);
        }

        public override int Update()
        {
           return 1;
        }
        public override void Draw()
        {
            XnaManager.Renderer.DrawString(TextManager.GetFont(), m_contents, m_position+m_origin, m_color, 0, Vector2.Zero,.75f, SpriteEffects.None, .96f);
        }
    }
}
