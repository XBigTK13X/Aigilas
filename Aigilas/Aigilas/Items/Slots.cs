using System;
using System.Collections.Generic;
using System.Linq;
using Agilas.Creatures;
using Agilas.Entities;

namespace Agilas.Items
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