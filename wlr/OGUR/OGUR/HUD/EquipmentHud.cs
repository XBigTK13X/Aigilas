using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Items;
using OGUR.Management;
using OGUR.Text;

namespace OGUR.HUD
{
    public class EquipmentHud:IHud
    {
        private Equipment m_equipment;
        private const string s_text = "Equipped";

        public EquipmentHud(ICreature owner, Equipment equipment): base(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2)
        {
            m_equipment = equipment;
        }

        public void Draw()
        {
            if (m_isVisible)
            {
                m_textHandler.Draw();
            }
        }

        private DefaultHudText equipHeading = new DefaultHudText();
        private DefaultHudText[] equipTexts = new DefaultHudText[100];
        public void Update()
        {
            if (equipTexts[0] == null)
            {
                equipHeading.Reset(s_text, 300, 30, GetHudOrigin());
                for (int jj = 0; jj < equipTexts.Count(); jj++)
                {
                    equipTexts[jj] = new DefaultHudText(.2f);
                }
            }
            m_textHandler.Update();
            m_textHandler.Clear();
            m_textHandler.Add(equipHeading);
            int ii = 0;
            foreach(var item in m_equipment.GetItems())
            {
                m_textHandler.WriteDefault(item.Key.ToString().Substring(0, 1) + ":" + item.Value.Name, 320, 60 + ii * 25, GetHudOrigin());
                ii++;
            }
        }
    }
}
