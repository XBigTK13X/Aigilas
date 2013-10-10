package aigilas.classes;

public enum PlayerClass {
    Sloth,
    Envy,
    Pride,
    Gluttony,
    Wrath,
    Lust,
    Greed,
    NoClass;

    public PlayerClassInfo Info;

    public static PlayerClass get(String value) {
        for (PlayerClass c : values()) {
            if (c.toString().equalsIgnoreCase(value)) {
                return c;
            }
        }
        return null;
    }
}
