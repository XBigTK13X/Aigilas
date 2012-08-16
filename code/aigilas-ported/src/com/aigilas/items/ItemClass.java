package com.aigilas.items;import com.xna.wrapper.*;import java.util.*;    public class ItemClass
    {
        public static final int NULL = 0;
        public static final int Melee_Weapon = 1;
        public static final int Ranged_Weapon = 2;
        public static final int Ranged_Ammo = 3;
        public static final int Ring = 4;
        public static final int Leggings = 5; 
        public static final int Torso_Garb = 6;
        public static final int Feet = 7;
        public static final int Head_Gear = 8;
        public static final int Gloves = 9;
        public static final int Shield = 10;
        public static final int LAST = 11;

        public static int[] Values =
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

        public static HashMap<Integer, String> Names = new HashMap<Integer, String>();
        static{
             Names.put(NULL,"NULL");
             Names.put(Melee_Weapon,"Melee Weapon");
             Names.put(Ranged_Weapon,"Ranged Weapon"); 
             Names.put(Ranged_Ammo,"Ranged Ammo");
             Names.put(Ring,"Ring");
             Names.put(Leggings,"Leggings"); 
             Names.put(Torso_Garb,"Torso Garb");
             Names.put(Feet,"Feet");
             Names.put(Head_Gear,"Head Gear");
             Names.put(Gloves,"Gloves");
             Names.put(Shield,"Shield");
             Names.put(LAST,"LAST");
        };
    }
