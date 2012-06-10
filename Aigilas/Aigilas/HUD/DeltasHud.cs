using System;
using Aigilas.Creatures;
using Aigilas.Items;
using SPX.Util;
using SPX.Core;

namespace Aigilas.HUD
{
    public class DeltasHud:IHud
    {
        private Equipment _equipment;

        public DeltasHud(ICreature owner, Equipment equipment): base(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2)
        {
            _equipment = equipment;
        }

        public void Draw()
        {
            if (_isVisible)
            {
                _textHandler.Draw();
            }
        }

        private GenericItem GetEquipmentIn(int slot)
        {
            if (_equipment.GetItems().ContainsKey(slot))
            {
                return _equipment.GetItems()[slot];
            }
            return null;
        }

        private const string delim = "|";
        private const string positive = "+";
        private const string title = "Deltas";
        private string display = "EMPTY";

        public void Update(GenericItem item,bool refresh)
        {
            if (_isVisible)
            {
                _textHandler.Update();
                _textHandler.Clear();
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
                _textHandler.WriteDefault(title, 30, 260, GetHudOrigin());
                _textHandler.WriteDefault(display, 30, 290, GetHudOrigin());
            }
        }
    }
}
