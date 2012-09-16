package aigilas.gods;

import aigilas.classes.*;
import aigilas.items.ItemClass;
import com.badlogic.gdx.graphics.Color;

public class God {
    public String NameText;
    public GodId Id;

    protected God(GodId id) {
        Id = id;
        NameText = Id.toString();
    }

    public Color getColor() {
        return Id.Tint;
    }

    public CreatureClass getCreatureClass() {
        switch (Id) {
            case ENVY:
                return new EnvyAcolyte();
            case GLUTTONY:
                return new GluttonyAcolyte();
            case GREED:
                return new GreedAcolyte();
            case LUST:
                return new LustAcolyte();
            case PRIDE:
                return new PrideAcolyte();
            case SLOTH:
                return new SlothAcolyte();
            case WRATH:
                return new WrathAcolyte();
        }
        return new NoClass();
    }

    public boolean isGoodSacrifice(ItemClass sacrifice) {
        return sacrifice == Id.Desires;
    }

    public boolean isBadSacrifice(ItemClass sacrifice) {
        return sacrifice == Id.Detests;
    }
}
