package aigilas.management;

import sps.graphics.SpriteDefinition;

import java.util.ArrayList;
import java.util.List;

public enum SpriteType {
    EMPTY(0),
    PLAYER_STAND(1),
    FLOOR(2),
    WALL(3),
    UPSTAIRS(4),
    DOWNSTAIRS(5),
    CREATURE(6),
    ITEM(7),
    SKILL_EFFECT(8),
    ALTAR(9),
    ZORB(10),
    MINION(11),
    COMBO_MARKER(13),
    WRATH(14),
    HAND(15),
    ENVY(16),
    SLOTH(17),
    GREED(18),
    GLUTTONY(19),
    LUST(20),
    PRIDE(21),
    WHEEL(22),
    WRATH_ACOLYTE(23),
    ENVY_ACOLYTE(24),
    PRIDE_ACOLYTE(25),
    SLOTH_ACOLYTE(26),
    GREED_ACOLYTE(27),
    GLUTTONY_ACOLYTE(28),
    LUST_ACOLYTE(29);

    public final int Index;
    private static ArrayList<SpriteDefinition> Definitions;

    private SpriteType(int index) {
        Index = index;
    }

    public static List<SpriteDefinition> getDefinitions(){
        if(Definitions == null){
            Definitions = new ArrayList<SpriteDefinition>();
            for(SpriteType s: values()){
                Definitions.add(new SpriteDefinition(s,s.Index,1));
            }
        }
        return Definitions;
    }
}
