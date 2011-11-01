using System;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Management;
using OGUR.Text;
using System.Text;
using OGUR.Util;
using System.Reflection;

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
            XnaManager.Renderer.Draw(m_menuBase, m_parent.GetHudOrigin(), new Rectangle(0, 0, 1, 1), new Color(0f,0f,0f,.4f), 0f, new Vector2(0,0), new Vector2(200,100), SpriteEffects.None, .95f);
            m_textHandler.Draw();
        }

        private string skillName = "";
        private readonly StringBuilder statBuilder = new StringBuilder(32, 32);
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
                statBuilder.Remove(0,statBuilder.Length);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.HEALTH)));
                statBuilder.Append("|");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.MANA)));
                statBuilder.Append("|");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.STRENGTH)));
                statBuilder.Append("|");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.DEFENSE)));
                statBuilder.Append("\n");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.WEIGHT)));
                statBuilder.Append("|");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.WISDOM)));
                statBuilder.Append("|");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.AGE)));
                statBuilder.Append("|");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.PIETY)));
                statBuilder.Append("|");
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.LUCK)));
                m_textHandler.Add(new DefaultHudText(skillName, 40, 5, m_parent,.2f));
                m_textHandler.Add(new DefaultHudText(statBuilder.ToString(),20,35,m_parent,.2f));
            }
        }

        public bool IsVisible()
        {
            return m_isVisible;
        }
    }
}
