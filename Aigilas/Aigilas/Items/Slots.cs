using System;
using System.Collections.Generic;
using System.Linq;
using Aigilas.Creatures;
using Aigilas.Entities;

namespace Aigilas.Items
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