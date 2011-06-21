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
    public class DeltasHud
    {
        private ICreature m_parent;
        private bool m_isVisible = false;
        private TextHandler m_textHandler = new TextHandler();

        public DeltasHud(ICreature owner)
        {
            m_parent = owner;
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

            var item = m_parent.GetCurrentInventorySelection();
            if (item != null)
            {
                var item2 = m_parent.GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass()));
                if (item2 != null)
                {
                    var stats = m_parent.GetEquipmentIn(Equipment.ClassToSlot(item.GetItemClass())).Modifers;
                    var stats2 = item.Modifers;

                    m_textHandler.Update();
                    m_textHandler.Clear();

                    m_textHandler.Add(new InventoryItemsText("Deltas", 30, 260, m_parent.GetPlayerIndex()));

                    int ii = 0;
                    string deltas = "";
                    foreach (decimal stat in stats.GetDeltas(stats2))
                    {
                        deltas += ((stat > 0) ? "+" : "") + stat + "|";
                    }
                    m_textHandler.Add(new InventoryItemsText(deltas, 30, 290, m_parent.GetPlayerIndex()));
                }
            }
        }
    }
}
