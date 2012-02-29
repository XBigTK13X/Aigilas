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
using SPX.Util;

namespace OGUR.HUD
{
    public class DeltasHud:IHud
    {
        private Equipment m_equipment;

        public DeltasHud(ICreature owner, Equipment equipment): base(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2)
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

        private GenericItem GetEquipmentIn(int slot)
        {
            if (m_equipment.GetItems().ContainsKey(slot))
            {
                return m_equipment.GetItems()[slot];
            }
            return null;
        }

        private const string delim = "|";
        private const string positive = "+";
        private const string title = "Deltas";
        private string display = "EMPTY";

        public void Update(GenericItem item,bool refresh)
        {
            if (m_isVisible)
            {
                m_textHandler.Update();
                m_textHandler.Clear();
                if (item != null && refresh)
                {
                    if (GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass())) != null)
                    {
                        StringSquisher.Clear();
                        foreach (var stat in GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass())).Modifers.GetDeltas(item.Modifers))
                        {
                            StringSquisher.Squish(((stat > 0) ? positive : String.Empty),StringStorage.Get(stat),delim);
                        }
                        display = StringSquisher.Flush();
                    }
                }
                m_textHandler.WriteDefault(title, 30, 260, GetHudOrigin());
                m_textHandler.WriteDefault(display, 30, 290, GetHudOrigin());
            }
        }
    }
}
