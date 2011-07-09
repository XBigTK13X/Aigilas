using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Management;
using OGUR.Text;

namespace OGUR.Skills
{
    public class SkillHud
    {
        private readonly ICreature m_parent;
        private static Texture2D m_menuBase;
        private bool m_isVisible = false;
        private readonly TextHandler m_textHandler = new TextHandler();

        public SkillHud(ICreature owner)
        {
            m_parent = owner;
            if (m_menuBase == null)
            {
                m_menuBase = XnaManager.GetContentManager().Load<Texture2D>("MenuBase");
            }
        }

        public void Toggle()
        {
            m_isVisible = !m_isVisible;
        }

        public void LoadContent()
        {
            m_menuBase = XnaManager.GetContentManager().Load<Texture2D>("MenuBase");
        }

        public void Draw()
        {
            if (!m_isVisible) return;
            SpriteBatch target = XnaManager.GetRenderTarget();
            //target.Begin();
            target.Begin(SpriteSortMode.BackToFront,
                         BlendState.AlphaBlend,
                         null,
                         null,
                         null,
                         null,
                         XnaManager.GetCamera().GetTransformation(XnaManager.GetGraphicsDevice().GraphicsDevice));
            target.Draw(m_menuBase, m_parent.GetHudOrigin(), new Rectangle(0, 0, 1, 1), new Color(0f,0f,0f,.4f), 0f, new Vector2(0,0), XnaManager.GetCenter(), SpriteEffects.None, 0f);
            target.End();
            m_textHandler.Draw();
        }
            
        public void Update()
        {
            m_textHandler.Update();
            m_textHandler.Clear();
            if(m_isVisible)
            {
                m_textHandler.Add(new DefaultHudText(m_parent.GetActiveSkill().ToString(), 20, 30, m_parent,.2f));
            }
        }

        public bool IsVisible()
        {
            return m_isVisible;
        }
    }
}
