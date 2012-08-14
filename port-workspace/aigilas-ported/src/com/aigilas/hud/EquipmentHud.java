package com.aigilas.hud;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.items.*;import com.spx.util.*;import com.spx.core.*;
    public class EquipmentHud extends IHud
    {
        private Equipment _equipment;
        private static final String __text = "Equipped";

        public EquipmentHud(ICreature owner, Equipment equipment){ super(owner, XnaManager.WindowWidth / 2, XnaManager.WindowHeight / 2);            _equipment = equipment;
        }

        public void Draw()
        {
            if (_isVisible)
            {
                _textHandler.Draw();
            }
        }

        private static final String sep = ":";
        private static final String newline = "\n";
        private String display = "EMPTY";
        private String title = "Equipped\n";

        public void Update(boolean refresh)
        {
            if (_isVisible)
            {
                _textHandler.Update();
                _textHandler.Clear();
                if (refresh)
                {
                    StringSquisher.Clear();
                    StringSquisher.Squish(title);                    HashMap<Integer,GenericItem> items =_equipment.GetItems();
                    for (Integer item:items.keySet())
                    {
                        StringSquisher.Squish(ItemSlot.Names[item].substring(0, 1), sep, items.get(item).Name, newline);
                    }
                    display = StringSquisher.Flush();
                }
                _textHandler.WriteDefault(display, 320, 30, GetHudOrigin());
            }
        }
    }
