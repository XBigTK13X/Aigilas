package aigilas.items;

import spx.core.Point2;
import spx.core.RNG;
import spx.entities.EntityManager;
import aigilas.creatures.Stats;

public class ItemFactory {
	private static final int itemGrowth = 3;
	private static ItemName itemType;

	public static GenericItem CreateRandomPlain(Point2 location, boolean onFloor) {
		if (location == null) {
			location = new Point2(-100, -100);
		}

		itemType = SelectRandomType();
		return (GenericItem) EntityManager.addObject(new GenericItem(new Stats(RNG.Rand.nextInt(itemGrowth), RNG.Rand.nextInt(itemGrowth), RNG.Rand.nextInt(itemGrowth), 0, 0, 0, 0, 0, 0, 0), null, null, itemType, location, onFloor));
	}

	public static GenericItem CreateRandomPlain(Point2 location) {
		return CreateRandomPlain(location, true);
	}

	public static GenericItem CreateRandomPlain() {
		return CreateRandomPlain(null, false);
	}

	public static GenericItem CreateRandomMagic() {
		return null;
		// $$$return new IItem(new Stats(1,1,1,1,1,1,0,1,0,0,0),)}
	}

	private static ItemName SelectRandomType() {
		return ItemName.values()[RNG.Next(1, ItemName.values().length)];
	}
}
