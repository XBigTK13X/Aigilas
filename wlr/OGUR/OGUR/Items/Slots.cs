using System;
using System.Collections.Generic;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
{
    public class Slots
    {
        public List<ItemSlot> Get;

        public Slots(List<ItemSlot> slots)
        {
            Get = new List<ItemSlot>(slots);
        }
    }
}