using System;
using System.Collections.Generic;
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
        private ItemType m_type;
        private Slots m_targetSlots;
        private bool m_isEquipped = false;

        public ItemClass GetItemClass()
        {
            switch (m_type)
            {
                case ItemType.Sword:
                    return ItemClass.Melee_Weapon;
                case ItemType.Arrow:
                    return ItemClass.Ranged_Ammo;
                case ItemType.Bow:
                    return ItemClass.Ranged_Weapon;
                case ItemType.Shield:
                    return ItemClass.Shield;
                case ItemType.Pants:
                    return ItemClass.Leggings;
                case ItemType.Dagger:
                    return ItemClass.Melee_Weapon;
                case ItemType.Staff:
                    return ItemClass.Melee_Weapon;
                case ItemType.Hood:
                    return ItemClass.Head_Gear;
                case ItemType.Shirt:
                    return ItemClass.Torso_Garb;
                default:
                    return ItemClass.NULL;
            }
        }

        public GenericItem(Stats modifiers, ItemSuffix suffix, ItemPrefix prefix, ItemType type, bool onGround = true,
                           int x = -1, int y = -1)
        {
            Setup(x, y, type);
            if (type == ItemType.NULL)
            {
                throw new Exception("Invalid type NULL passed into the GenericItem factory!");
            }
            m_suffix = suffix;
            m_prefix = prefix;
            m_type = type;
            m_targetSlots = GetSlotFromType(type);
            Name = (m_prefix == ItemPrefix.NULL ? "" : Enum.GetName(typeof (ItemPrefix), m_prefix) + " ") +
                   Enum.GetName(typeof (ItemType), m_type) +
                   (m_suffix == ItemSuffix.NULL ? "" : " " + Enum.GetName(typeof (ItemSuffix), m_suffix));
            Modifers = new Stats(modifiers);
        }

        protected void Setup(int x, int y, ItemType type)
        {
            Initialize(x, y, SpriteFromItem(type), GameObjectType.ITEM);
        }

        public void Equip()
        {
            m_isEquipped = true;
        }

        public void Unequip()
        {
            m_isEquipped = false;
        }

        public bool isEquipped()
        {
            return m_isEquipped;
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

        private SpriteType SpriteFromItem(ItemType item)
        {
            return SpriteType.ITEM;
        }

        private Slots GetSlotFromType(ItemType type)
        {
            switch(type)
            {
                case ItemType.Sword:
                    return new Slots(new List<ItemSlot>(){ItemSlot.RIGHT_HAND,ItemSlot.LEFT_HAND});
                case ItemType.Pants:
                    return new Slots(new List<ItemSlot>(){ItemSlot.LEGS});
                default:
                    return new Slots(new List<ItemSlot>());
            }
        }
    }
}