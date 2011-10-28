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

namespace OGUR.Storage
{
    public class InventoryHud
    {
        private readonly ICreature m_parent;
        private static Texture2D m_menuBase;
        private bool m_isVisible = false;
        private ItemClass m_currentClass = (ItemClass) 1;
        private readonly Inventory m_inventory;
        private int m_endingItem = 4, m_startingItem = 0;
        private Dictionary<GenericItem, int> m_currentClassItems;
        private readonly TextHandler m_textHandler = new TextHandler();
        private GenericItem m_currentSelectedItem = null;

        public InventoryHud(ICreature owner)
        {
            m_parent = owner;
            m_inventory = owner.GetInventory();
            if (m_menuBase == null)
            {
                m_menuBase = XnaManager.GetContentManager().Load<Texture2D>("MenuBase");
            }
        }

        public void Toggle()
        {
            m_isVisible = !m_isVisible;
        }

        public void LoadContent()
        {
            m_menuBase = XnaManager.GetContentManager().Load<Texture2D>("MenuBase");
        }

        public void Draw()
        {
            if (m_isVisible)
            {
                XnaManager.Renderer.Draw(m_menuBase, m_parent.GetHudOrigin(), new Rectangle(0, 0, 1, 1), Color.White, 0f, new Vector2(0,0), XnaManager.GetCenter(), SpriteEffects.None, 0f);
                m_textHandler.Draw();
            }
        }

        private void HandleInput()
        {
            if (InputManager.IsPressed(InputManager.Commands.MoveLeft, m_parent.GetPlayerIndex()))
            {
                m_currentClass--;
                if (m_currentClass <= (ItemClass)0)
                {
                    m_currentClass = (ItemClass)OGUR.Util.EnumUtil.GetValues(typeof(ItemClass)).Length - 2;
                }
                m_startingItem = 0;
                m_endingItem = 4;
            }

            if (InputManager.IsPressed(InputManager.Commands.MoveRight, m_parent.GetPlayerIndex()))
            {
                m_currentClass++;
                if (m_currentClass >= ItemClass.LAST)
                {
                    m_currentClass = (ItemClass)1;
                }
                m_startingItem = 0;
                m_endingItem = 4;
            }

            if (InputManager.IsPressed(InputManager.Commands.MoveDown, m_parent.GetPlayerIndex()))
            {
                if (m_startingItem < m_currentClassItems.Count() - 1)
                {
                    m_startingItem++;
                    m_endingItem++;
                }
            }

            if (InputManager.IsPressed(InputManager.Commands.MoveUp, m_parent.GetPlayerIndex()))
            {
                if (m_startingItem > 0)
                {
                    m_startingItem--;
                    m_endingItem--;
                }
            }
        }
        public void Update()
        {
            m_textHandler.Update();
            m_textHandler.Clear();
            if(m_isVisible)
            {
                HandleInput();
                UpdateInventoryDisplay();
            }
        }

        private void UpdateInventoryDisplay()
        {
            m_textHandler.Add(new DefaultHudText(m_currentClass.ToString().Replace("_", " "), 20, 30,
                                                       m_parent));
            m_currentClassItems = m_inventory.GetItems(m_currentClass);
            if (m_currentClassItems.Count > 0)
            {
                int ii = 0;
                foreach (var item in m_currentClassItems.Keys)
                {
                    if (ii == m_startingItem)
                    {
                        m_currentSelectedItem = item;
                    }
                    if(!m_parent.IsEquipped(item)&&m_inventory.GetItemCount(item)<=0)
                    {
                        continue;
                    }

                    if (ii >= m_startingItem && ii < m_endingItem && ii < m_currentClassItems.Keys.Count())
                    {
                        string displayText = ii + ")"
                                                  +((m_parent.IsEquipped(item))? "~" : "")
                                                  + item.Name +
                                                  ((m_currentClassItems[item] > -1) ? " x" + m_currentClassItems[item] :
                                                  "");
                        m_textHandler.Add(new DefaultHudText(displayText, 50,
                                                                 60 + 25 * (ii - m_startingItem),
                                                                 m_parent));
                    }
                    ii++;
                }
                if (InputManager.IsPressed(InputManager.Commands.Confirm, m_parent.GetPlayerIndex()))
                {
                    m_parent.Equip(m_currentSelectedItem);
                }
                if (InputManager.IsPressed(InputManager.Commands.Cancel, m_parent.GetPlayerIndex()))
                {
                    m_parent.Drop(m_currentSelectedItem);
                }
            }
        }

        public GenericItem GetCurrentSelection()
        {
            return m_currentSelectedItem;
        }

        public bool IsVisible()
        {
            return m_isVisible;
        }
    }
}
