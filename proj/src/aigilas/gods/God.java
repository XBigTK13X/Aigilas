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

    public Color GetColor() {
        return Id.Tint;
    }

    public CreatureClass GetClass() {
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

    public boolean IsGoodSacrifice(ItemClass sacrifice) {
        return sacrifice == Id.Desires;
    }

    public boolean IsBadSacrifice(ItemClass sacrifice) {
        return sacrifice == Id.Detests;
    }
}
