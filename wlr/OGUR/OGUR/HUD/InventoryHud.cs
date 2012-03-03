using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Items;
using SPX.Util;
using SPX.Core;
using OGUR.Management;

namespace OGUR.HUD
{
    public class InventoryHud:IHud
    {
        private int m_currentClass = ItemClass.Values[1];
        private readonly Inventory m_inventory;
        private readonly Equipment m_equipment;
        private int m_endingItem = 4, m_startingItem = 0;
        private Dictionary<GenericItem, int> m_currentClassItems;
        private GenericItem m_currentSelectedItem = null;

        private EquipmentHud m_equipHud;
        private DeltasHud m_deltas;

        public InventoryHud(ICreature owner,Inventory inventory,Equipment equipment):base(owner,XnaManager.WindowWidth/2,XnaManager.WindowHeight/2)
        {
            m_inventory = inventory;
            m_equipment = equipment;
            m_deltas = new DeltasHud(owner,equipment);
            m_equipHud = new EquipmentHud(owner, equipment);
            m_currentClassItems = m_inventory.GetItems(m_currentClass);
        }

        public void Draw()
        {
            if (m_isVisible)
            {
                XnaManager.Renderer.Draw(m_menuBase,GetHudOrigin(), new Rectangle(0, 0, 1, 1), Color.White, 0f, new Vector2(0,0), XnaManager.GetCenter(), SpriteEffects.None,ZDepth.HudBG);
                m_textHandler.Draw();
                m_deltas.Draw();
                m_equipHud.Draw();
            }
        }

        private void HandleInput()
        {
            if (Input.IsPressed(Commands.CycleLeft, m_parent.GetPlayerIndex()))
            {
                m_currentClass--;
                if (m_currentClass <= ItemClass.NULL)
                {
                    m_currentClass = ItemClass.Values.Count() - 2;
                }
                m_startingItem = 0;
                m_endingItem = 4;
                forceRefresh = true;
            }

            if (Input.IsPressed(Commands.CycleRight, m_parent.GetPlayerIndex()))
            {
                m_currentClass++;
                if (m_currentClass >= ItemClass.LAST)
                {
                    m_currentClass = ItemClass.Values[1];                    
                }
                m_startingItem = 0;
                m_endingItem = 4;
                forceRefresh = true;
            }

            if (Input.IsPressed(Commands.MoveDown, m_parent.GetPlayerIndex()))
            {
                if (m_startingItem < m_currentClassItems.Count() - 1)
                {
                    m_startingItem++;
                    m_endingItem++;
                    forceRefresh = true;
                }
            }

            if (Input.IsPressed(Commands.MoveUp, m_parent.GetPlayerIndex()))
            {
                if (m_startingItem > 0)
                {
                    m_startingItem--;
                    m_endingItem--;
                    forceRefresh = true;
                }
            }
            if (Input.IsPressed(Commands.Confirm, m_parent.GetPlayerIndex()))
            {
                m_parent.Equip(m_currentSelectedItem);
                forceRefresh = true;
            }
            if (Input.IsPressed(Commands.Cancel, m_parent.GetPlayerIndex()))
            {
                m_parent.Drop(m_currentSelectedItem);
                forceRefresh = true;
            }
        }
        public void Update()
        {
            if (m_isVisible)
            {
                HandleInput();
                m_textHandler.Update();
                m_deltas.Update(m_currentSelectedItem,forceRefresh);
                m_equipHud.Update(forceRefresh);
                m_textHandler.Clear();
                UpdateInventoryDisplay();
                if (forceRefresh)
                {
                    forceRefresh = false;
                }
            }
        }

        public override void Toggle()
        {
            base.Toggle();
            m_deltas.Toggle();
            m_equipHud.Toggle();
            forceRefresh = true;
        }

        private static readonly Dictionary<int, string> s_classStrings = new Dictionary<int, string>();

        private string GetClassDisplay()
        {
            if(!s_classStrings.ContainsKey(m_currentClass))
            {
                s_classStrings.Add(m_currentClass, ItemClass.Names[m_currentClass]);
            }
            return s_classStrings[m_currentClass];
        }

        private const string s_delim = ")";
        private const string s_equipDelim = "~";
        private const string s_seper = " x";
        private const string s_newline = "\n";

        private string displayString = "";
        private bool forceRefresh = false;

        private void UpdateInventoryDisplay()
        {
            m_textHandler.WriteDefault(GetClassDisplay(), 20, 30,GetHudOrigin());
            m_currentClassItems = m_inventory.GetItems(m_currentClass);
            if (m_currentClassItems.Count > 0)
            {
                int ii = 0;
                if (forceRefresh)
                {
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
                    displayString = StringSquisher.Flush();       
                }
                m_textHandler.WriteDefault(displayString, 50, 60, GetHudOrigin());
            }
        }
    }
}
