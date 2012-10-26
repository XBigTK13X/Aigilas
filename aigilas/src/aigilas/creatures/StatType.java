package aigilas.creatures;

public enum StatType {
    Health("H"),
    Mana("M"),
    Strength("S"),
    Defense("D"),
    Wisdom("W"),
    Luck("L"),
    Age("A"),
    Height("HE"),
    Weight("WE"),
    Move_Cool_Down("MC"),
    Piety("P"),
    Regen("R");

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
