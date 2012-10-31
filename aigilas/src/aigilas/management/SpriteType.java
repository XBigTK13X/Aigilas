package aigilas.energygement;

import sps.graphics.SpriteDefinition;

import java.util.ArrayList;
import java.util.List;

public enum SpriteType {
    Empty(0),
    Player_Stand(1, 2),
    Floor(2),
    Wall(3),
    Upstairs(4),
    Downstairs(5),
    Creature(6),
    Item(7),
    Skill_Effect(8),
    Altar(9),
    Zorb(10),
    Minion(11),
    Dummy(13),
    Wrath(14),
    Hand(15),
    Envy(16),
    Sloth(17),
    Greed(18),
    Gluttony(19),
    Lust(20),
    Pride(21),
    Wheel(22),
    Wrath_Acolyte(23),
    Envy_Acolyte(24),
    Pride_Acolyte(25),
    Sloth_Acolyte(26),
    Greed_Acolyte(27),
    Gluttony_Acolyte(28),
    Lust_Acolyte(29);

    public final int Index;
    public final int Frames;
    private static ArrayList<SpriteDefinition> Definitions;

    private SpriteType(int index) {
        this(index, 1);
    }

    private SpriteType(int index, int frames) {
        Frames = frames;
        Index = index;
    }

    public static List<SpriteDefinition> getDefinitions() {
        if (Definitions == null) {
            Definitions = new ArrayList<SpriteDefinition>();
            for (SpriteType s : values()) {
                Definitions.add(new SpriteDefinition(s, s.Index, s.Frames));
            }
        }
        return Definitions;
    }
}
