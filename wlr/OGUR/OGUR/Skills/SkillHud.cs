using System;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Management;
using OGUR.Text;
using System.Text;

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
                m_menuBase = XnaManager.GetMenuBaseAsset();
            }
        }

        public void Toggle()
        {
            m_isVisible = !m_isVisible;
        }

        public void LoadContent()
        {
            m_menuBase = XnaManager.GetMenuBaseAsset();
        }

        public void Draw()
        {
            if (!m_isVisible) return;
            XnaManager.Renderer.Draw(m_menuBase, m_parent.GetHudOrigin(), new Rectangle(0, 0, 1, 1), new Color(0f,0f,0f,.4f), 0f, new Vector2(0,0), new Vector2(200,100), SpriteEffects.None, 0f);
            m_textHandler.Draw();
        }

        private string statDisplay = "";
        private string skillName = "";
        private StringBuilder statBuilder = new StringBuilder();
        public void Update()
        {
            m_textHandler.Update();
            m_textHandler.Clear();
            if(m_isVisible)
            {
                if (m_parent.GetActiveSkillName() != skillName)
                {
                    skillName = m_parent.GetActiveSkillName();
                }
                statDisplay = "";
                statBuilder.Clear();
                foreach(StatType stat in OGUR.Util.EnumUtil.GetValues(typeof (StatType)))
                {
                    statBuilder.Append((int)m_parent.Get(stat) + "|");
                }

                m_textHandler.Add(new DefaultHudText(skillName, 40, 30, m_parent,.2f));
                m_textHandler.Add(new DefaultHudText(statBuilder.ToString(),5,50,m_parent,.2f));
            }
        }

        public bool IsVisible()
        {
            return m_isVisible;
        }
    }
}
