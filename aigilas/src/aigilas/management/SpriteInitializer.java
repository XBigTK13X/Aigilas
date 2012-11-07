package aigilas.management;

import sps.bridge.SpriteTypes;
import sps.graphics.ISpriteInitializer;
import sps.graphics.SpriteDefinition;

import java.util.List;

public class SpriteInitializer implements ISpriteInitializer {
    @Override
    public List<SpriteDefinition> getSprites() {
        return SpriteTypes.getDefs();
    }
}
