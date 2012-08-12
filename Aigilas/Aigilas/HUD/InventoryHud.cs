using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Aigilas.Creatures;
using Aigilas.Items;
using SPX.Util;
using SPX.Core;
using Aigilas.Management;
using SPX.IO;

namespace Aigilas.HUD
{
    public class InventoryHud:IHud
    {
        private int _currentClass = ItemClass.Values[1];
        private readonly Inventory _inventory;
        private readonly Equipment _equipment;
        private int _endingItem = 4;
        private int _startingItem = 0;
        private Dictionary<GenericItem, int> _currentClassItems;
        private GenericItem _currentSelectedItem = null;

        private EquipmentHud _equipHud;
        private DeltasHud _deltas;

        public InventoryHud(ICreature owner,Inventory inventory,Equipment equipment):base(owner,XnaManager.WindowWidth/2,XnaManager.WindowHeight/2)
        {
            _inventory = inventory;
            _equipment = equipment;
            _deltas = new DeltasHud(owner,equipment);
            _equipHud = new EquipmentHud(owner, equipment);
            _currentClassItems = _inventory.GetItems(_currentClass);
        }

        public void Draw()
        {
            if (_isVisible)
            {
                XnaManager.Renderer.Draw(_menuBase,GetHudOrigin(), new Rectangle(0, 0, 1, 1), Color.Black, 0f, new Vector2(0,0), XnaManager.GetCenter(), SpriteEffects.None,ZDepth.HudBG);
                _textHandler.Draw();
                _deltas.Draw();
                _equipHud.Draw();
            }
        }

        private void HandleInput()
        {
            if (Input.IsActive(Commands.CycleLeft, _parent.GetPlayerIndex()))
            {
                _currentClass--;
                if (_currentClass <= ItemClass.NULL)
                {
                    _currentClass = ItemClass.Values.Count() - 2;
                }
                _startingItem = 0;
                _endingItem = 4;
                forceRefresh = true;
            }

            if (Input.IsActive(Commands.CycleRight, _parent.GetPlayerIndex()))
            {
                _currentClass++;
                if (_currentClass >= ItemClass.LAST)
                {
                    _currentClass = ItemClass.Values[1];                    
                }
                _startingItem = 0;
                _endingItem = 4;
                forceRefresh = true;
            }

            if (Input.IsActive(Commands.MoveDown, _parent.GetPlayerIndex()))
            {
                if (_startingItem < _currentClassItems.Count() - 1)
                {
                    _startingItem++;
                    _endingItem++;
                    forceRefresh = true;
                }
            }

            if (Input.IsActive(Commands.MoveUp, _parent.GetPlayerIndex()))
            {
                if (_startingItem > 0)
                {
                    _startingItem--;
                    _endingItem--;
                    forceRefresh = true;
                }
            }
            if (Input.IsActive(Commands.Confirm, _parent.GetPlayerIndex()))
            {
                _parent.Equip(_currentSelectedItem);
                forceRefresh = true;
            }
            if (Input.IsActive(Commands.Cancel, _parent.GetPlayerIndex()))
            {
                _parent.Drop(_currentSelectedItem);
                forceRefresh = true;
            }
        }
        public void Update()
        {
            if (_isVisible)
            {
                HandleInput();
                _textHandler.Update();
                _deltas.Update(_currentSelectedItem,forceRefresh);
                _equipHud.Update(forceRefresh);
                _textHandler.Clear();
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
            _deltas.Toggle();
            _equipHud.Toggle();
            forceRefresh = true;
        }

        private static readonly Dictionary<int, string> __classStrings = new Dictionary<int, string>();

        private string GetClassDisplay()
        {
            if(!__classStrings.ContainsKey(_currentClass))
            {
                __classStrings.Add(_currentClass, ItemClass.Names[_currentClass]);
            }
            return __classStrings[_currentClass];
        }

        private const string __delim = ")";
        private const string __equipDelim = "~";
        private const string __seper = " x";
        private const string __newline = "\n";

        private string displayString = "";
        private bool forceRefresh = false;

        private void UpdateInventoryDisplay()
        {
            _textHandler.WriteDefault(GetClassDisplay(), 20, 30,GetHudOrigin());
            _currentClassItems = _inventory.GetItems(_currentClass);
            if (_currentClassItems.Count > 0)
            {
                int ii = 0;
                if (forceRefresh)
                {
                    StringSquisher.Clear();
                    foreach (var item in _currentClassItems.Keys)
                    {
                        if (ii == _startingItem)
                        {
                            _currentSelectedItem = item;
                        }
                        if(!_equipment.IsRegistered(item)&&_inventory.GetItemCount(item)<=0)
                        {
                            continue;
                        }

                   
                        if (ii >= _startingItem && ii < _endingItem && ii < _currentClassItems.Keys.Count())
                        {
                            StringSquisher.Squish
                            (
                                StringStorage.Get(ii), __delim,
                                (_equipment.IsRegistered(item)) ? __equipDelim : String.Empty,
                                item.Name);
                            if (_currentClassItems[item] > -1)
                            {
                                StringSquisher.Squish(__seper, StringStorage.Get(_currentClassItems[item]));
                            }
                            StringSquisher.Squish(__newline);
                        }
                        ii++;
                    }
                    displayString = StringSquisher.Flush();       
                }
                _textHandler.WriteDefault(displayString, 50, 60, GetHudOrigin());
            }
        }
    }
}
