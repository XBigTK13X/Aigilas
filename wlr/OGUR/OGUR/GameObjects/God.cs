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
    public class GodId
    {
        public const int LUST = 0;
        public const int GREED = 1;
        public const int SLOTH = 2;
        public const int ENVY = 3;
        public const int WRATH = 4;
        public const int GLUTTONY = 5;
        public const int PRIDE = 6;

        public static readonly int[] Values = 
        {
            LUST,
            GREED,
            SLOTH,
            ENVY,
            WRATH,
            GLUTTONY,
            PRIDE
        };

        public static readonly string[] Names = 
        {
            "Lust",
            "Greed",
            "Sloth",
            "Envy",
            "Wrath",
            "Gluttony",
            "Pride"
        };
    }
    public class God
    {
        public static God Get(int name)
        {
            return s_gods.Where(o => o.m_name == name).FirstOrDefault();
        }


        private static readonly List<God> s_gods = new List<God>()
                                              {
                                                  new God(Color.Pink, GodId.LUST, ItemClass.Leggings, ItemClass.Melee_Weapon),
                                                  new God(Color.Gold, GodId.GREED, ItemClass.Head_Gear, ItemClass.Gloves),
                                                  new God(Color.Silver, GodId.SLOTH, ItemClass.Shield, ItemClass.Head_Gear),
                                                  new God(Color.LightGoldenrodYellow, GodId.ENVY, ItemClass.Torso_Garb, ItemClass.Ranged_Weapon),
                                                  new God(Color.Red, GodId.WRATH, ItemClass.Melee_Weapon, ItemClass.Ranged_Ammo),
                                                  new God(Color.LightGreen, GodId.GLUTTONY, ItemClass.Gloves, ItemClass.Torso_Garb),
                                                  new God(Color.LightBlue, GodId.PRIDE, ItemClass.Ring, ItemClass.Feet)
                                              };

        private Color m_color;
        private int m_name;
        private int m_goodSacrificeClass;
        private int m_badSacrificeClass;

        public string NameText;

        protected God(Color color, int name, int goodSacrifice, int badSacrifice)
        {
            m_color = color;
            m_name = name;
            m_goodSacrificeClass = goodSacrifice;
            m_badSacrificeClass = badSacrifice;
            NameText = GodId.Names[m_name];
        }

        public Color GetColor()
        {
            return m_color;
        }

        public CreatureClass GetClass()
        {
            switch (m_name)
            {
                case GodId.ENVY: return new EnvyAcolyte();
                case GodId.GLUTTONY: return new GluttonyAcolyte();
                case GodId.GREED: return new GreedAcolyte();
                case GodId.LUST: return new LustAcolyte();
                case GodId.PRIDE: return new PrideAcolyte();
                case GodId.SLOTH: return new SlothAcolyte();
                case GodId.WRATH: return new WrathAcolyte();
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