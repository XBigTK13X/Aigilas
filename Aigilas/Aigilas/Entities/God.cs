using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Aigilas.Creatures;
using Aigilas.Items;
using Aigilas.Classes;

namespace Aigilas.Gods
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
            return __gods.Where(o => o._name == name).FirstOrDefault();
        }


        private static readonly List<God> __gods = new List<God>()
                                              {
                                                  new God(Color.Pink, GodId.LUST, ItemClass.Leggings, ItemClass.Melee_Weapon),
                                                  new God(Color.Gold, GodId.GREED, ItemClass.Head_Gear, ItemClass.Gloves),
                                                  new God(Color.Silver, GodId.SLOTH, ItemClass.Shield, ItemClass.Head_Gear),
                                                  new God(Color.LightGoldenrodYellow, GodId.ENVY, ItemClass.Torso_Garb, ItemClass.Ranged_Weapon),
                                                  new God(Color.Red, GodId.WRATH, ItemClass.Melee_Weapon, ItemClass.Ranged_Ammo),
                                                  new God(Color.LightGreen, GodId.GLUTTONY, ItemClass.Gloves, ItemClass.Torso_Garb),
                                                  new God(Color.LightBlue, GodId.PRIDE, ItemClass.Ring, ItemClass.Feet)
                                              };

        private Color _color;
        private int _name;
        private int _goodSacrificeClass;
        private int _badSacrificeClass;

        public string NameText;

        protected God(Color color, int name, int goodSacrifice, int badSacrifice)
        {
            _color = color;
            _name = name;
            _goodSacrificeClass = goodSacrifice;
            _badSacrificeClass = badSacrifice;
            NameText = GodId.Names[_name];
        }

        public Color GetColor()
        {
            return _color;
        }

        public CreatureClass GetClass()
        {
            switch (_name)
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
            return sacrifice == _goodSacrificeClass;
        }

        public bool IsBadSacrifice(int sacrifice)
        {
            return sacrifice == _badSacrificeClass;
        }
    }
}   