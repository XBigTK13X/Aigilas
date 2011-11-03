using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Items
    
{
    public class ItemFactory
    {
        private static Random rand = new Random();
        private const int itemGrowth = 3;
        private static ItemName itemType;

        public static GenericItem CreateRandomPlain(Point2 location = null,bool onFloor = false)
        {
            if(location == null)
            {
                location = new Point2(-100,-100);
            }

            itemType = SelectRandomType();
            return
                (GenericItem)
                GameplayObjectManager.AddObject(new GenericItem(new Stats(rand.Next(itemGrowth), rand.Next(itemGrowth), rand.Next(itemGrowth), 0, 0, 0, 0, 0, 0, 0, 0),
                                                                ItemSuffix.NULL, ItemPrefix.NULL, itemType,
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