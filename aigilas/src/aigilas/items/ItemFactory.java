package aigilas.items;

import aigilas.creatures.Stats;
import sps.core.Point2;
import sps.core.RNG;
import sps.entities.EntityManager;

public class ItemFactory {
    private static final int itemGrowth = 3;
    private static ItemName itemType;

    public static GenericItem createRandomPlain(Point2 location, boolean onFloor) {
        if (location == null) {
            location = new Point2(-100, -100);
        }

        itemType = selectRandomType();
        return (GenericItem) EntityManager.get().addObject(new GenericItem(new Stats(RNG.Rand.nextInt(itemGrowth), RNG.Rand.nextInt(itemGrowth), RNG.Rand.nextInt(itemGrowth), 0, 0, 0, 0, 0, 0, 0), null, null, itemType, location, onFloor));
    }

    public static GenericItem createRandomPlain(Point2 location) {
        return createRandomPlain(location, true);
    }

    public static GenericItem createRandomPlain() {
        return createRandomPlain(null, false);
    }

    public static GenericItem createRandomMagic() {
        return null;
    }

    private static ItemName selectRandomType() {
        return ItemName.values()[RNG.next(1, ItemName.values().length)];
    }
}
