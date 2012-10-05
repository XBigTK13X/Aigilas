package aigilas.creatures;

public enum StatType {
    HEALTH,
    MANA,
    STRENGTH,
    WISDOM,
    DEFENSE,
    LUCK,
    AGE,
    HEIGHT,
    WEIGHT,
    MOVE_COOL_DOWN,
    PIETY,
    REGEN;

    public static StatType get(String value) {
        for(StatType s:values()){
            if(s.toString().equalsIgnoreCase(value)){
                return s;
            }
        }
        return null;
    }
}
