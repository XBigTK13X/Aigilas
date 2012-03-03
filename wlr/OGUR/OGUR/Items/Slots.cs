using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Creatures;
using OGUR.Entities;

namespace OGUR.Items
{
    public class Slots
    {
       private List<int> _slots; 
       public Slots(IEnumerable<int> itemSlots)
       {
           _slots = new List<int>(itemSlots);
       }
       
    }
}