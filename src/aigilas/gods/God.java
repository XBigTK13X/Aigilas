package aigilas.gods;

import aigilas.classes.*;
import aigilas.items.ItemClass;
import com.badlogic.gdx.graphics.Color;

public class God {
    public final String NameText;
    public final GodId Id;

    protected God(GodId id) {
        Id = id;
        NameText = Id.toString();
    }

    public Color getColor() {
        return Id.Tint;
    }

    public CreatureClass getCreatureClass() {
        switch (Id) {
            case Envy:
                return new EnvyAcolyte();
            case Gluttony:
                return new GluttonyAcolyte();
            case Greed:
                return new GreedAcolyte();
            case Lust:
                return new LustAcolyte();
            case Pride:
                return new PrideAcolyte();
            case Sloth:
                return new SlothAcolyte();
            case Wrath:
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
