using Agilas.Creatures;
using Agilas.Items;
using SPX.Util;
using SPX.Core;

namespace Agilas.HUD
{
    public class EquipmentHud:IHud
    {
        private Equipment _equipment;
        private const string __text = "Equipped";

        public EquipmentHud(ICreature owner, Equipment equipment): base(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2)
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

        private const string sep = ":";
        private const string newline = "\n";
        private string display = "EMPTY";
        private string title = "Equipped\n";

        public void Update(bool refresh)
        {
            if (_isVisible)
            {
                _textHandler.Update();
                _textHandler.Clear();
                if (refresh)
                {
                    StringSquisher.Clear();
                    StringSquisher.Squish(title);
                    foreach (var item in _equipment.GetItems())
                    {
                        StringSquisher.Squish(ItemSlot.Names[item.Key].Substring(0, 1), sep, item.Value.Name, newline);
                    }
                    display = StringSquisher.Flush();
                }
                _textHandler.WriteDefault(display, 320, 30, GetHudOrigin());
            }
        }
    }
}
