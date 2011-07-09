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

namespace OGUR.Items
{
    public class EquipmentHud
    {
        private ICreature m_parent;
        private bool m_isVisible = false;
        private Equipment m_equipment;
        private TextHandler m_textHandler = new TextHandler();

        public EquipmentHud(ICreature owner)
        {
            m_parent = owner;
            m_equipment = owner.GetEquipment();
        }

        public void Toggle()
        {
            m_isVisible = !m_isVisible;
        }

        public void Draw()
        {
            if (m_isVisible)
            {
                m_textHandler.Draw();
            }
        }

        public void Update()
        {
            m_textHandler.Update();
            m_textHandler.Clear();
            m_textHandler.Add(new DefaultHudText("Equipped", 300, 30,m_parent));
            int ii = 0;
            foreach(var item in m_equipment.GetItems())
            {
                m_textHandler.Add(new DefaultHudText(item.Key.ToString().Substring(0,1)+":"+item.Value.Name,320,60+ii*25,m_parent));
                ii++;
            }
        }
    }
}
