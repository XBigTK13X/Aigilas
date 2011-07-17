using System;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
    
{
    public class ItemFactory
    {
        private static Random rand = new Random();
        public static GenericItem CreateRandomPlain(bool onFloor=false,int x = -100,int y = -100)
        {
            return
                (GenericItem)
                GameplayObjectManager.AddObject(new GenericItem(new Stats(0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
                                                                ItemSuffix.NULL, ItemPrefix.NULL, SelectRandomType(),
                                                                onFloor, x, y));
        }
        public static GenericItem CreateRandomPlain(int x, int y)
        {
            return CreateRandomPlain(true, x, y);
        }
        public static GenericItem CreateRandomMagic()
        {
            return null;
            //return new IItem(new Stats(1,1,1,1,1,1,0,1,0,0,0),)            }
        }
        private static ItemName SelectRandomType()
        {
            return ItemName.Dagger;
            //return (ItemName)Enum.GetValues(typeof (ItemName)).GetValue(rand.Next(1, Enum.GetValues(typeof (ItemName)).Length));
        }
    }
}