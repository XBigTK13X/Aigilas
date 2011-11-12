using System.Collections.Generic;
namespace OGUR.Items
{
    public class ItemClass
    {
        public const int NULL = 0;
        public const int Melee_Weapon = 1;
        public const int Ranged_Weapon = 2;
        public const int Ranged_Ammo = 3;
        public const int Ring = 4;
        public const int Leggings = 5; 
        public const int Torso_Garb = 6;
        public const int Feet = 7;
        public const int Head_Gear = 8;
        public const int Gloves = 9;
        public const int Shield = 10;
        public const int LAST = 11;

        public static readonly List<int> Values = new List<int>()
        {
            NULL,
            Melee_Weapon,
            Ranged_Weapon, 
            Ranged_Ammo,
            Ring,
            Leggings, 
            Torso_Garb,
            Feet,
            Head_Gear,
            Gloves,
            Shield,
            LAST
        };

        public static readonly Dictionary<int, string> Names = new Dictionary<int, string>()
        {
            {NULL,"NULL"},
            {Melee_Weapon,"Melee Weapon"},
            {Ranged_Weapon,"Ranged Weapon"}, 
            {Ranged_Ammo,"Ranged Ammo"},
            {Ring,"Ring"},
            {Leggings,"Leggings"}, 
            {Torso_Garb,"Torso Garb"},
            {Feet,"Feet"},
            {Head_Gear,"Head Gear"},
            {Gloves,"Gloves"},
            {Shield,"Shield"},
            {LAST,"LAST"}
        };
    }
}
