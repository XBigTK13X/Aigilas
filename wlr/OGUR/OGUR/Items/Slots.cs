using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
{
    public class Slots
    {
       private List<int> m_slots; 
       public Slots(IEnumerable<int> itemSlots)
       {
           m_slots = new List<int>(itemSlots);
       }
       
    }
}