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
using OGUR.Util;

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

        private const string sep = ":";
        private const string newline = "\n";
        private string display = "EMPTY";
        private string title = "Equipped\n";

        public void Update(bool refresh)
        {
            if (m_isVisible)
            {
                m_textHandler.Update();
                m_textHandler.Clear();
                if (refresh)
                {
                    StringSquisher.Clear();
                    StringSquisher.Squish(title);
                    foreach (var item in m_equipment.GetItems())
                    {
                        StringSquisher.Squish(ItemSlot.Names[item.Key].Substring(0, 1), sep, item.Value.Name, newline);
                    }
                    display = StringSquisher.Flush();
                }
                m_textHandler.WriteDefault(display, 320, 30, GetHudOrigin());
            }
        }
    }
}
