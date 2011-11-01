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

        public void Draw()
        {
            if (!m_isVisible) return;
            XnaManager.Renderer.Draw(m_menuBase, GetHudOrigin(), new Rectangle(0, 0, 1, 1), new Color(0f,0f,0f,.4f), 0f, new Vector2(0,0), m_dimensions, SpriteEffects.None, .95f);
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
                m_textHandler.Add(new DefaultHudText(skillName, 40, 5,GetHudOrigin(),.2f));
                m_textHandler.Add(new DefaultHudText(statBuilder.ToString(),20,35,GetHudOrigin(),.2f));
            }
        }
    }
}
