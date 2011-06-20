using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
{
    public class Slots
    {
       private List<ItemSlot> m_slots; 
       public Slots(IEnumerable<ItemSlot> itemSlots)
       {
           m_slots = new List<ItemSlot>(itemSlots);
       }

    }
}