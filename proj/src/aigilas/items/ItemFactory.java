package aigilas.items;

import aigilas.creatures.Stats;
import spx.core.Point2;
import spx.core.RNG;
import spx.entities.EntityManager;

public class ItemFactory {
    private static final int itemGrowth = 3;
    private static ItemName itemType;

    public static GenericItem createRandomPlain(Point2 location, boolean onFloor) {
        if (location == null) {
            location = new Point2(-100, -100);
        }

        itemType = selectRandomType();
        return (GenericItem) EntityManager.addObject(new GenericItem(new Stats(RNG.Rand.nextInt(itemGrowth), RNG.Rand.nextInt(itemGrowth), RNG.Rand.nextInt(itemGrowth), 0, 0, 0, 0, 0, 0, 0), null, null, itemType, location, onFloor));
    }

    public static GenericItem createRandomPlain(Point2 location) {
        return createRandomPlain(location, true);
    }

    public static GenericItem createRandomPlain() {
        return createRandomPlain(null, false);
    }

    public static GenericItem createRandomMagic() {
        return null;
        // $$$return new IItem(new Stats(1,1,1,1,1,1,0,1,0,0,0),)}
    }

    private static ItemName selectRandomType() {
        return ItemName.values()[RNG.next(1, ItemName.values().length)];
    }
}
