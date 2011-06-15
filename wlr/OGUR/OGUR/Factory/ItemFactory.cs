using System;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Items;

namespace OGUR.Factory
    
{
    public class ItemFactory : GameplayObject
    {
        private static Random rand = new Random();
        public static GenericItem CreateRandomPlain()
        {
            return new GenericItem(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),ItemSuffix.NULL,ItemPrefix.NULL, SelectRandomType());
        }
        public static GenericItem CreateRandomMagic()
        {
            return null;
            //return new IItem(new Stats(1,1,1,1,1,1,0,1,0,0,0),)            }
        }
        private static ItemType SelectRandomType()
        {
            return (ItemType)Enum.GetValues(typeof (ItemType)).GetValue(rand.Next(0, Enum.GetValues(typeof (ItemType)).Length));
        }
    }
}