using System;
using OGUR.Creatures;
using SPX.Core;
using SPX.Entities;

namespace OGUR.Items
    
{
    public class ItemFactory
    {
        private const int itemGrowth = 3;
        private static int itemType;

        public static GenericItem CreateRandomPlain(Point2 location = null,bool onFloor = false)
        {
            if(location == null)
            {
                location = new Point2(-100,-100);
            }

            itemType = SelectRandomType();
            return
                (GenericItem)
                EntityManager.AddObject(new GenericItem(new Stats(RNG.Rand.Next(itemGrowth), RNG.Rand.Next(itemGrowth), RNG.Rand.Next(itemGrowth), 0, 0, 0, 0, 0, 0, 0),
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
        private static int SelectRandomType()
        {
            return ItemName.Values[(RNG.Rand.Next(1, ItemName.Values.Length))];
        }
    }
}