using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;

namespace OGUR.HUD
{
    class InventoryScreensManager
    {
        static private List<Inventory> m_inventories = new List<Inventory>();
        private static Texture2D m_menuBase;

        static public void Toggle(int playerIndex)
        {
            if (m_inventories.Where(o => o.GetPlayerId() == playerIndex).Count() > 0)
            {
                m_inventories.Remove(m_inventories.Where(o => o.GetPlayerId() == playerIndex).First());
            }
            else
            {
                m_inventories.Add(new Inventory(playerIndex));
            }
        }

        static public void LoadContent()
        {
            m_menuBase = XnaManager.GetContentManager().Load<Texture2D>("MenuBase");
        }

        static public void Draw()
        {
            foreach(Inventory items in m_inventories)
            {
                items.Draw();
            }
        }

        static public Texture2D GetMenuBase()
        {
            return m_menuBase;
        }

        static public void Update()
        {
            for (int index = 0; index < m_inventories.Count; index++)
            {
                Inventory items = m_inventories[index];
                if(items.IsVisible())
                {
                    items.Update();
                }
                else
                {
                    m_inventories.Remove(items);
                    index--;
                }
            }
        }
    }
}
