package aigilas.management;

import spx.graphics.ISpriteInitializer;
import spx.graphics.SpriteDefinition;

import java.util.ArrayList;
import java.util.List;

public class SpriteInitializer implements ISpriteInitializer {
    private SpriteDefinition make(SpriteType type, int index, int frames) {
        return new SpriteDefinition(type, index, frames);
    }

    @Override
    public List<SpriteDefinition> getSprites() {
        ArrayList<SpriteDefinition> result = new ArrayList<SpriteDefinition>();
        result.add(make(SpriteType.EMPTY, 0, 1));
        result.add(make(SpriteType.PLAYER_STAND, 1, 2));
        result.add(make(SpriteType.FLOOR, 2, 1));
        result.add(make(SpriteType.WALL, 3, 1));
        result.add(make(SpriteType.UPSTAIRS, 4, 1));
        result.add(make(SpriteType.DOWNSTAIRS, 5, 1));
        result.add(make(SpriteType.CREATURE, 6, 1));
        result.add(make(SpriteType.ITEM, 7, 1));
        result.add(make(SpriteType.SKILL_EFFECT, 8, 1));
        result.add(make(SpriteType.ALTAR, 9, 1));
        result.add(make(SpriteType.ZORB, 10, 1));
        result.add(make(SpriteType.MINION, 11, 1));
        result.add(make(SpriteType.COMBO_MARKER, 13, 1));
        result.add(make(SpriteType.WRATH, 14, 1));
        result.add(make(SpriteType.HAND, 15, 1));
        result.add(make(SpriteType.ENVY, 16, 1));
        result.add(make(SpriteType.SLOTH, 17, 1));
        result.add(make(SpriteType.GREED, 18, 1));
        result.add(make(SpriteType.GLUTTONY, 19, 1));
        result.add(make(SpriteType.LUST, 20, 1));
        result.add(make(SpriteType.PRIDE, 21, 1));
        result.add(make(SpriteType.WHEEL, 22, 1));
        return result;
    }
}
