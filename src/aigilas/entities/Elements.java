package aigilas.entities;

import com.badlogic.gdx.graphics.Color;

public enum Elements {
    Normal(0, Color.GRAY),
    Fire(1, Color.RED),
    Water(2, Color.BLUE),
    Earth(3, Color.GREEN),
    Air(4, Color.YELLOW),
    Light(5, Color.WHITE),
    Dark(6, Color.BLACK),
    Physical(7, Color.ORANGE),
    Mental(8, Color.MAGENTA);

    public final Color Tint;
    public final int Value;

    private Elements(int value, Color color) {
        Value = value;
        Tint = color;
    }

    public static Elements get(String element) {
        for (Elements e : values()) {
            if (e.toString().equalsIgnoreCase(element)) {
                return e;
            }
        }
        return null;
    }
}
