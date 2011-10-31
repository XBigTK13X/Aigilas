using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
    
{
    public class ItemFactory
    {
        private static Random rand = new Random();
        public static GenericItem CreateRandomPlain(Point2 location = null,bool onFloor = false)
        {
            if(location == null)
            {
                location = new Point2(-100,-100);
            }

            return
                (GenericItem)
                GameplayObjectManager.AddObject(new GenericItem(new Stats(rand.Next(5), rand.Next(5), rand.Next(5), 0, 0, 0, 0, 0, 0, 0, 0),
                                                                ItemSuffix.NULL, ItemPrefix.NULL, SelectRandomType(),
                                                                location,onFloor));
        }
        public static GenericItem CreateRandomPlain(Point2 location)
        {
            return CreateRandomPlain(location, true);
        }
        public static GenericItem CreateRandomMagic()
        {
            return null;
            //return new IItem(new Stats(1,1,1,1,1,1,0,1,0,0,0),)}
        }
        private static ItemName SelectRandomType()
        {
            return (ItemName)OGUR.Util.EnumUtil.GetValues(typeof(ItemName))[(rand.Next(1, OGUR.Util.EnumUtil.GetValues(typeof(ItemName)).Count))];
        }
    }
}