using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using OGUR.Creatures;
using OGUR.Items;
using OGUR.Classes;

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
        private int m_goodSacrificeClass;
        private int m_badSacrificeClass;

        public string NameText;

        protected God(Color color, Name name, int goodSacrifice, int badSacrifice)
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

        public CreatureClass GetClass()
        {
            switch (m_name)
            {
                case Name.ENVY: return new EnvyAcolyte();
                case Name.GLUTTONY: return new GluttonyAcolyte();
                case Name.GREED: return new GreedAcolyte();
                case Name.LUST: return new LustAcolyte();
                case Name.PRIDE: return new PrideAcolyte();
                case Name.SLOTH: return new SlothAcolyte();
                case Name.WRATH: return new WrathAcolyte();
            }
            return new NoClass();
        }

        public bool IsGoodSacrifice(int sacrifice)
        {
            return sacrifice == m_goodSacrificeClass;
        }

        public bool IsBadSacrifice(int sacrifice)
        {
            return sacrifice == m_badSacrificeClass;
        }
    }
}   