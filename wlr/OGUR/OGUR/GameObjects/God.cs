using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using OGUR.Creatures;
using OGUR.Items;

namespace OGUR.Gods
{
    public class God
    {
        public enum Name
        {
            LUST,
            GREED,
            SLOTH,
            ENVY,
            WRATH,
            GLUTTONY,
            PRIDE
        }

        public static God Get(Name name)
        {
            return s_gods.Where(o => o.m_name == name).FirstOrDefault();
        }


        private static readonly List<God> s_gods = new List<God>()
                                              {
                                                  new God(Color.Pink, Name.LUST, ItemClass.Leggings, ItemClass.Melee_Weapon),
                                                  new God(Color.Gold, Name.GREED, ItemClass.Head_Gear, ItemClass.Gloves),
                                                  new God(Color.Silver, Name.SLOTH, ItemClass.Shield, ItemClass.Head_Gear),
                                                  new God(Color.LightGoldenrodYellow, Name.ENVY, ItemClass.Torso_Garb, ItemClass.Ranged_Weapon),
                                                  new God(Color.Red, Name.WRATH, ItemClass.Melee_Weapon, ItemClass.Ranged_Ammo),
                                                  new God(Color.LightGreen, Name.GLUTTONY, ItemClass.Gloves, ItemClass.Torso_Garb),
                                                  new God(Color.LightBlue, Name.PRIDE, ItemClass.Ring, ItemClass.Feet)
                                              };

        private Color m_color;
        private Name m_name;
        private ItemClass m_goodSacrificeClass;
        private ItemClass m_badSacrificeClass;

        public string NameText;

        protected God(Color color, Name name, ItemClass goodSacrifice, ItemClass badSacrifice)
        {
            m_color = color;
            m_name = name;
            m_goodSacrificeClass = goodSacrifice;
            m_badSacrificeClass = badSacrifice;
            NameText = m_name.ToString().ToUpperInvariant();
        }

        public Color GetColor()
        {
            return m_color;
        }

        public bool IsGoodSacrifice(ItemClass sacrifice)
        {
            return sacrifice == m_goodSacrificeClass;
        }

        public bool IsBadSacrifice(ItemClass sacrifice)
        {
            return sacrifice == m_badSacrificeClass;
        }
    }
}   