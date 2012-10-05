package aigilas.entities;

import com.badlogic.gdx.graphics.Color;

public enum Elements {
    NORMAL(0, Color.GRAY),
    FIRE(1, Color.RED),
    WATER(2, Color.BLUE),
    EARTH(3, Color.GREEN),
    AIR(4, Color.YELLOW),
    LIGHT(5, Color.WHITE),
    DARK(6, Color.BLACK),
    PHYSICAL(7, Color.ORANGE),
    MENTAL(8, Color.MAGENTA);

    public final Color Tint;
    public final int Value;

    private Elements(int value, Color color) {
        Value = value;
        Tint = color;
    }

    public static Elements get(String element) {
        for(Elements e:values()){
            if(e.toString().equalsIgnoreCase(element)){
                return e;
            }
        }
        return null;
    }
}
