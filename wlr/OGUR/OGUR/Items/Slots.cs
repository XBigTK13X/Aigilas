using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
{
    public class Slots
    {
        public static ItemSlot ClassToSlot(ItemClass iClass)
        {
            switch(iClass)
            {
                case ItemClass.Melee_Weapon:
                    return ItemSlot.RIGHT_HAND;
                case ItemClass.Ranged_Weapon:
                    return ItemSlot.RIGHT_HAND;
                case ItemClass.Ranged_Ammo:
                    return ItemSlot.LEFT_HAND ;
                case ItemClass.Ring:
                    return ItemSlot.LEFT_FINGER;
                case ItemClass.Leggings: 
                    return ItemSlot.LEGS;
                case ItemClass.Torso_Garb:
                    return ItemSlot.TORSO;
                case ItemClass.Feet:
                    return ItemSlot.FEET;
                case ItemClass.Head_Gear:
                    return ItemSlot.HEAD;
                case ItemClass.Gloves:
                    return ItemSlot.HANDS;
                case ItemClass.Shield:
                    return ItemSlot.LEFT_HAND;
            }
            return ItemSlot.NULL;
        }

        private readonly List<ItemSlot> Get;


        public void Fill(ItemSlot slot)
        {
            Get.Add(slot);
        }

        public void Fill(ItemClass iClass)
        {
            Fill(ClassToSlot(iClass));
        }

        public void Remove(ItemClass iClass)
        {
            Get.Remove(ClassToSlot(iClass));
        }

        public Slots(IEnumerable<ItemSlot> slots)
        {
            Get = new List<ItemSlot>(slots);
        }

        public bool IsFull(ItemSlot item)
        {
            return Get.Contains(item);
        }

        public bool IsFull(ItemClass iClass)
        {
            return IsFull(ClassToSlot(iClass));
        }
    }
}