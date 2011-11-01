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
            if (m_equipment.GetItems().Where(o => o.Key == slot).Count() > 0)
            {
                return m_equipment.GetItems().Where(o => o.Key == slot).First().Value;
            }
            return null;
        }
        public void Update(GenericItem item)
        {
            if (item != null)
            {
                var item2 = GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass()));
                if (item2 != null)
                {
                    var stats = GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass())).Modifers;
                    var stats2 = item.Modifers;

                    m_textHandler.Update();
                    m_textHandler.Clear();

                    m_textHandler.Add(new DefaultHudText("Deltas", 30, 260,GetHudOrigin()));

                    string deltas = "";
                    foreach (float stat in stats.GetDeltas(stats2))
                    {
                        deltas += ((stat > 0) ? "+" : "") + stat + "|";
                    }
                    m_textHandler.Add(new DefaultHudText(deltas, 30, 290, GetHudOrigin()));
                }
            }
        }
    }
}
