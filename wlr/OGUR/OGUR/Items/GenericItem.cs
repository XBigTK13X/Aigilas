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
        private string m_suffix;
        private string m_prefix;
        private int m_type;
        private Slots m_targetSlots;

        private const string s_spacingCharacter = " ";

        public int GetItemClass()
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

        private void Initialize(string suffix, string prefix,int type, Slots targetSlots,Stats modifiers,Point2 location)
        {
            Setup(location, type);
            m_suffix = suffix;
            m_prefix = prefix;
            m_type = type;
            m_targetSlots = GetSlotFromType(type);
            Name = (m_prefix == ItemPrefix.NULL ? String.Empty :  m_prefix + s_spacingCharacter) +
                    ItemName.Names[m_type] +
                   (m_suffix == ItemSuffix.NULL ? String.Empty : s_spacingCharacter + m_suffix);
            Modifers = new Stats(modifiers);
        }

        public GenericItem(GenericItem item,Point2 location)
        {
            Initialize(item.m_suffix, item.m_prefix, item.m_type,item.m_targetSlots, item.Modifers,location);
        }

        public GenericItem(Stats modifiers, string suffix, string prefix, int type, Point2 location,bool onGround = true)
        {
            if (type == ItemName.NULL)
            {
                throw new Exception("Invalid type NULL passed into the GenericItem factory!");
            }
            Initialize(m_suffix,m_prefix,type,GetSlotFromType(type),modifiers,location);
        }

        protected void Setup(Point2 location, int type)
        {
            Initialize(location, SpriteFromItem(type), GameObjectType.ITEM,Depth.Item);
        }

        private Player m_currentTarget;
        public override void Update()
        {
            base.Update();
            if (m_isOnBoard)
            {
                m_currentTarget = GameplayObjectManager.GetTouchingPlayer(this);
                if (m_currentTarget != null)
                {
                    if (m_currentTarget.IsInteracting())
                    {
                        m_currentTarget.PickupItem(this);
                    }
                }
            }
        }

        private SpriteType SpriteFromItem(int item)
        {
            return SpriteType.ITEM;
        }

        private Slots GetSlotFromType(int type)
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
                if (Name != gI.Name)
                {
                    return false;
                }
                foreach (string stat in StatType.Values)
                {
                    if (Modifers.Get(stat) != gI.Modifers.Get(stat))
                    {
                        return false;
                    }
                }    
                return true;
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

        public float GetStatBonus(string stat)
        {
            return Modifers.Get(stat);
        }
    }
}