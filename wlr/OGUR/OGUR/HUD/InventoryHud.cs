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
    public class InventoryHud:IHud
    {
        private ItemClass m_currentClass = (ItemClass) 1;
        private readonly Inventory m_inventory;
        private readonly Equipment m_equipment;
        private int m_endingItem = 4, m_startingItem = 0;
        private Dictionary<GenericItem, int> m_currentClassItems;
        private GenericItem m_currentSelectedItem = null;

        private DeltasHud m_deltas;

        public InventoryHud(ICreature owner,Inventory inventory,Equipment equipment):base(owner,XnaManager.WindowWidth/2,XnaManager.WindowHeight/2)
        {
            m_inventory = inventory;
            m_equipment = equipment;
            m_deltas = new DeltasHud(owner,equipment);
            m_currentClassItems = m_inventory.GetItems(m_currentClass);
        }

        public void Draw()
        {
            if (m_isVisible)
            {
                XnaManager.Renderer.Draw(m_menuBase,GetHudOrigin(), new Rectangle(0, 0, 1, 1), Color.White, 0f, new Vector2(0,0), XnaManager.GetCenter(), SpriteEffects.None,.95f);
                m_textHandler.Draw();
                m_deltas.Draw();
            }
        }

        private void HandleInput()
        {
            if (InputManager.IsPressed(InputManager.Commands.MoveLeft, m_parent.GetPlayerIndex()))
            {
                m_currentClass--;
                if (m_currentClass <= (ItemClass)0)
                {
                    m_currentClass = (ItemClass)OGUR.Util.EnumUtil.GetValues(typeof(ItemClass)).Count() - 2;
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
            m_deltas.Update(m_currentSelectedItem);
            m_textHandler.Clear();
            if(m_isVisible)
            {
                HandleInput();
                UpdateInventoryDisplay();
            }
        }

        public override void Toggle()
        {
            base.Toggle();
            m_deltas.Toggle();
        }

        private static readonly Dictionary<ItemClass, string> s_classStrings = new Dictionary<ItemClass, string>();

        private string GetClassDisplay()
        {
            if(!s_classStrings.ContainsKey(m_currentClass))
            {
                s_classStrings.Add(m_currentClass, m_currentClass.ToString().Replace("_", " "));
            }
            return s_classStrings[m_currentClass];
        }

        private const string s_delim = ")";
        private const string s_equipDelim = "~";
        private const string s_seper = " x";
        private const string s_newline = "\n";

        private int lastStarting = -1;
        private string displayString = "";
        private void UpdateInventoryDisplay()
        {
            m_textHandler.WriteDefault(GetClassDisplay(), 20, 30,GetHudOrigin());
            m_currentClassItems = m_inventory.GetItems(m_currentClass);
            if (m_currentClassItems.Count > 0)
            {
                int ii = 0;
                StringSquisher.Clear();
                foreach (var item in m_currentClassItems.Keys)
                {
                    if (ii == m_startingItem)
                    {
                        m_currentSelectedItem = item;
                    }
                    if(!m_equipment.IsRegistered(item)&&m_inventory.GetItemCount(item)<=0)
                    {
                        continue;
                    }

                   
                    if (ii >= m_startingItem && ii < m_endingItem && ii < m_currentClassItems.Keys.Count())
                    {
                        StringSquisher.Squish
                        (
                            StringStorage.Get(ii), s_delim,
                            (m_equipment.IsRegistered(item)) ? s_equipDelim : String.Empty,
                            item.Name);
                        if (m_currentClassItems[item] > -1)
                        {
                            StringSquisher.Squish(s_seper, StringStorage.Get(m_currentClassItems[item]));
                        }
                        StringSquisher.Squish(s_newline);
                    }
                    ii++;
                }
                if (lastStarting != m_startingItem)
                {
                    displayString = StringSquisher.Flush();
                    lastStarting = m_startingItem;
                }
                m_textHandler.WriteDefault(displayString, 50, 60, GetHudOrigin());
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
    }
}
