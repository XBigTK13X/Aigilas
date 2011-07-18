using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Items
{
    public class Equipment
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

        private readonly Dictionary<ItemSlot, GenericItem> m_slots = new Dictionary<ItemSlot, GenericItem>();
        private readonly ICreature m_parent;


        public Equipment(ICreature owner)
        {
            m_parent = owner;
        }

        public void Register(GenericItem item)
        {
            var itemSlot = ClassToSlot(item.GetItemClass());
            if(m_slots.ContainsKey(itemSlot))
            {
                m_parent.Unequip(m_slots[itemSlot]);
                m_slots[itemSlot] = item;
            }
            else
            {
                m_slots.Add(itemSlot, item);
            }
            
        }

        public void Unregister(GenericItem item)
        {
            var itemSlot = ClassToSlot(item.GetItemClass());
            if (m_slots.ContainsKey(itemSlot))
            {
                m_parent.PickupItem(m_slots[itemSlot]);
                m_slots.Remove(itemSlot);
            }
        }

        public bool IsRegistered(GenericItem item)
        {
            var itemClass = ClassToSlot(item.GetItemClass());
            if(m_slots.ContainsKey(itemClass))
            {
                return (item == m_slots[itemClass]) ;
            }
            return false;
        }

        public float CalculateBonus(StatType stat)
        {
            return m_slots.Sum(item => item.Value.GetStatBonus(stat));
        }

        public Dictionary<ItemSlot,GenericItem> GetItems()
        {
            return m_slots;
        }
    }
}
