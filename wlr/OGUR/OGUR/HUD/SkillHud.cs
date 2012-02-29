using System;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Management;
using OGUR.Text;
using System.Text;
using SPX.Util;
using System.Reflection;
using OGUR.Skills;
using OGUR.GameObjects;

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
            XnaManager.Renderer.Draw(m_menuBase, GetHudOrigin(), new Rectangle(0, 0, 1, 1), new Color(0f, 0f, 0f, .4f), 0f, new Vector2(0, 0), m_dimensions, SpriteEffects.None, Depth.HudBG);
            m_textHandler.Draw();
        }


        private string skillName = "";
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
                m_displayStats.Set(StatType.HEALTH,m_parent.Get(StatType.HEALTH));
                m_displayStats.Set(StatType.MANA,m_parent.Get(StatType.MANA));
                m_displayStats.Set(StatType.STRENGTH,m_parent.Get(StatType.STRENGTH));
                m_displayStats.Set(StatType.DEFENSE,m_parent.Get(StatType.DEFENSE));
                m_displayStats.Set(StatType.WISDOM, m_parent.Get(StatType.WISDOM));
                m_displayStats.Set(StatType.AGE, m_parent.Get(StatType.AGE));
                m_displayStats.Set(StatType.PIETY, m_parent.Get(StatType.PIETY));
                m_displayStats.Set(StatType.LUCK, m_parent.Get(StatType.LUCK));

                statString = StringSquisher.Build(
                    StringStorage.Get(m_parent.Get(StatType.HEALTH)), s_separator,
                    StringStorage.Get(m_parent.Get(StatType.MANA)), s_separator,
                    StringStorage.Get(m_parent.Get(StatType.STRENGTH)), s_separator,
                    StringStorage.Get(m_parent.Get(StatType.DEFENSE)),s_newline,
                    StringStorage.Get(m_parent.Get(StatType.WEIGHT)),s_separator,
                    StringStorage.Get(m_parent.Get(StatType.WISDOM)),s_separator,
                    StringStorage.Get(m_parent.Get(StatType.AGE)),s_separator,
                    StringStorage.Get(m_parent.Get(StatType.PIETY)),s_separator,
                    StringStorage.Get(m_parent.Get(StatType.LUCK)));
            }
            return statString;
        }

        public void Update()
        {
            if (m_isVisible)
            {
                m_textHandler.Update();
                m_textHandler.Clear();
                if (m_parent.GetActiveSkillName() != skillName)
                {
                    skillName = m_parent.GetActiveSkillName();
                }
                m_textHandler.WriteDefault(skillName, 40, 5, GetHudOrigin());
                m_textHandler.WriteDefault(GetStatString(), 20, 35, GetHudOrigin());
            }
        }
    }
}
