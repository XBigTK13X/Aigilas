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

        private GenericItem GetEquipmentIn(ItemSlot slot)
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

        public void Update(GenericItem item)
        {
            if (item != null)
            {
                if (GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass())) != null)
                {
                    m_textHandler.Update();
                    m_textHandler.Clear();

                    m_textHandler.WriteDefault(title, 30, 260, GetHudOrigin());

                    StringSquisher.Clear();
                    foreach (float stat in GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass())).Modifers.GetDeltas(item.Modifers))
                    {
                        StringSquisher.Squish(((stat > 0) ? positive : String.Empty) + stat + delim);
                    }
                    m_textHandler.WriteDefault(StringSquisher.Flush(), 30, 290, GetHudOrigin());
                }
            }
        }
    }
}
