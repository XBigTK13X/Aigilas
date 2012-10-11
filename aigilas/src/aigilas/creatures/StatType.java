package aigilas.creatures;

public enum StatType {
    HEALTH("H"),
    MANA("M"),
    STRENGTH("S"),
    WISDOM("W"),
    DEFENSE("D"),
    LUCK("L"),
    AGE("A"),
    HEIGHT("HE"),
    WEIGHT("WE"),
    MOVE_COOL_DOWN("MC"),
    PIETY("P"),
    REGEN("R");

    private String shortHand;

    private StatType(String shortHand) {
        this.shortHand = shortHand;
    }

    public static StatType get(String value) {
        for (StatType s : values()) {
            if (s.toString().equalsIgnoreCase(value)) {
                return s;
            }
        }
        return null;
    }

    public static StatType getShort(String value) {
        for (StatType s : values()) {
            if (s.shortHand.equalsIgnoreCase(value)) {
                return s;
            }
        }
        return null;
    }
}
