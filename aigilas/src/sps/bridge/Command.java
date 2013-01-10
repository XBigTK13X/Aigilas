package sps.bridge;

import sps.io.Buttons;
import sps.io.Keys;

public class Command {
    private Buttons _button;
    private Keys _key;
    private String _name;
    public Context Context;

    public Command() {

    }

    public Command(String name, Context context) {
        Context = context;
        _name = name;
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

    @Override
    public int hashCode() {
        return _name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.hashCode() == hashCode();
    }
}
