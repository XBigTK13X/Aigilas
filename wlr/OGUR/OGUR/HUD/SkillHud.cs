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
using OGUR.Skills;

namespace OGUR.HUD
{
    public class SkillHud:IHud
    {
        public SkillHud(ICreature owner) : base(owner,200,100) { }
        private const string s_separator = "|";
        private const string s_newline = "\n";

        public void Draw()
        {
            if (!m_isVisible) return;
            XnaManager.Renderer.Draw(m_menuBase, GetHudOrigin(), new Rectangle(0, 0, 1, 1), new Color(0f,0f,0f,.4f), 0f, new Vector2(0,0), m_dimensions, SpriteEffects.None, .95f);
            m_textHandler.Draw();
        }
        
        private string skillName = "";
        private readonly StringBuilder statBuilder = new StringBuilder(32, 32);
        private readonly Stats m_displayStats = new Stats(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f);
        private string statString = "";

        private string GetStatString()
        {
            if (m_parent.Get(StatType.HEALTH) == m_displayStats.Get(StatType.HEALTH) &&
                m_parent.Get(StatType.MANA) == m_displayStats.Get(StatType.MANA) &&
                m_parent.Get(StatType.STRENGTH) == m_displayStats.Get(StatType.STRENGTH) &&
                m_parent.Get(StatType.DEFENSE) == m_displayStats.Get(StatType.DEFENSE) &&
                m_parent.Get(StatType.WISDOM) == m_displayStats.Get(StatType.WISDOM) &&
                m_parent.Get(StatType.AGE) == m_displayStats.Get(StatType.AGE) &&
                m_parent.Get(StatType.PIETY) == m_displayStats.Get(StatType.PIETY) &&
                m_parent.Get(StatType.LUCK) == m_displayStats.Get(StatType.LUCK))
            {
                return statString;
            }
            else
            {
                statBuilder.Remove(0, statBuilder.Length);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.HEALTH)));
                statBuilder.Append(s_separator);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.MANA)));
                statBuilder.Append(s_separator);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.STRENGTH)));
                statBuilder.Append(s_separator);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.DEFENSE)));
                statBuilder.Append(s_newline);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.WEIGHT)));
                statBuilder.Append(s_separator);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.WISDOM)));
                statBuilder.Append(s_separator);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.AGE)));
                statBuilder.Append(s_separator);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.PIETY)));
                statBuilder.Append(s_separator);
                statBuilder.Append(StringStorage.Get(m_parent.Get(StatType.LUCK)));
                statString = statBuilder.ToString();
            }
            return statString;
        }

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
                m_textHandler.Add(new DefaultHudText(skillName, 40, 5,GetHudOrigin(),.2f));
                m_textHandler.Add(new DefaultHudText(GetStatString(),20,35,GetHudOrigin(),.2f));
            }
        }
    }
}
