package sps.bridge;

import sps.io.Buttons;
import sps.io.Keys;

public class Command {

    private static int commandCount = 0;
    private Buttons _button;
    private Keys _key;
    private String _name;
    public final Context Context;
    private int _ordinal;

    public Command(String name, Context context) {
        Context = context;
        _name = name;
        _ordinal = commandCount++;
    }

    public void bind(Buttons button, Keys key) {
        _button = button;
        _key = key;
    }

    public Buttons button() {
        return _button;
    }

    public Keys key() {
        return _key;
    }

    public String name() {
        return _name;
    }

    public int ordinal() {
        return _ordinal;
    }
}
