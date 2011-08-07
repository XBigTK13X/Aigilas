using System;
using System.Collections.Generic;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Management;
using OGUR.Sprites;

namespace OGUR.Items
{
    public class GenericItem : GameplayObject
    {
        public Stats Modifers;
        public String Name;
        private ItemSuffix m_suffix;
        private ItemPrefix m_prefix;
        private ItemName m_type;
        private Slots m_targetSlots;

        public ItemClass GetItemClass()
        {
            switch (m_type)
            {
                case ItemName.Sword:
                    return ItemClass.Melee_Weapon;
                case ItemName.Arrow:
                    return ItemClass.Ranged_Ammo;
                case ItemName.Bow:
                    return ItemClass.Ranged_Weapon;
                case ItemName.Shield:
                    return ItemClass.Shield;
                case ItemName.Pants:
                    return ItemClass.Leggings;
                case ItemName.Dagger:
                    return ItemClass.Melee_Weapon;
                case ItemName.Staff:
                    return ItemClass.Melee_Weapon;
                case ItemName.Hood:
                    return ItemClass.Head_Gear;
                case ItemName.Shirt:
                    return ItemClass.Torso_Garb;
                default:
                    return ItemClass.NULL;
            }
        }

        private void Initialize(ItemSuffix suffix, ItemPrefix prefix,ItemName type, Slots targetSlots,Stats modifiers,Point2 location)
        {
            Setup(location, type);
            m_suffix = suffix;
            m_prefix = prefix;
            m_type = type;
            m_targetSlots = GetSlotFromType(type);
            Name = (m_prefix == ItemPrefix.NULL ? "" : Enum.GetName(typeof(ItemPrefix), m_prefix) + " ") +
                   Enum.GetName(typeof(ItemName), m_type) +
                   (m_suffix == ItemSuffix.NULL ? "" : " " + Enum.GetName(typeof(ItemSuffix), m_suffix));
            Modifers = new Stats(modifiers);
        }

        public GenericItem(GenericItem item,Point2 location)
        {
            Initialize(item.m_suffix, item.m_prefix, item.m_type,item.m_targetSlots, item.Modifers,location);
        }

        public GenericItem(Stats modifiers, ItemSuffix suffix, ItemPrefix prefix, ItemName type, Point2 location,bool onGround = true)
        {
            if (type == ItemName.NULL)
            {
                throw new Exception("Invalid type NULL passed into the GenericItem factory!");
            }
            Initialize(m_suffix,m_prefix,type,GetSlotFromType(type),modifiers,location);
        }

        protected void Setup(Point2 location, ItemName type)
        {
            Initialize(location, SpriteFromItem(type), GameObjectType.ITEM);
        }

        public override void Update()
        {
            base.Update();
            if (m_isOnBoard)
            {
                foreach (ICreature player in GameplayObjectManager.GetObjects(CreatureType.PLAYER))
                {
                    if (Collision.HitTest.IsTouching(player, this) &&
                        InputManager.IsPressed(InputManager.Commands.Confirm, player.GetPlayerIndex()))
                    {
                        Hide();
                        player.PickupItem(this);
                    }
                }
            }
        }

        private SpriteType SpriteFromItem(ItemName item)
        {
            return SpriteType.ITEM;
        }

        private Slots GetSlotFromType(ItemName type)
        {
            switch (type)
            {
                case ItemName.Sword:
                    return new Slots(new List<ItemSlot>() {ItemSlot.RIGHT_HAND, ItemSlot.LEFT_HAND});
                case ItemName.Pants:
                    return new Slots(new List<ItemSlot>() {ItemSlot.LEGS});
                default:
                    return new Slots(new List<ItemSlot>());
            }
        }

        public override bool Equals(object obj)
        {
            if(obj.GetType()==typeof(GenericItem))
            {
                var gI = (GenericItem)obj;
                if (Name == gI.Name && Modifers.GetHashCode() == gI.Modifers.GetHashCode())
                {
                    return true;
                }
                return false;
            }
            return false;
        }

        public override int GetHashCode()
        {
            return Name.GetHashCode() + Modifers.GetHashCode();
        }

        public static bool operator ==(GenericItem a, GenericItem b)
        {
            if (System.Object.ReferenceEquals(a, b))
            {
                return true;
            }

            if (((object) a == null) || ((object) b == null))
            {
                return false;
            }

            return a.Equals(b);
        }

        public static bool operator !=(GenericItem a, GenericItem b)
        {
            return !(a == b);
        }

        public float GetStatBonus(StatType stat)
        {
            return Modifers.Get(stat);
        }
    }
}