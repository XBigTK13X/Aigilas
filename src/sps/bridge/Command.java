package sps.bridge;

import sps.io.ControllerInput;
import sps.io.Keys;

public class Command implements Comparable {
    private ControllerInput _controllerInput;
    private Keys _key;
    private String _name;
    public Context Context;

    public Command() {

    }

    public Command(String name, Context context) {
        Context = context;
        _name = name;
    }

    public void bind(ControllerInput controllerInput, Keys key) {
        _controllerInput = controllerInput;
        _key = key;
    }

    public ControllerInput controllerInput() {
        return _controllerInput;
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

    @Override
    public int compareTo(Object o) {
        return name().compareTo(((Command) o).name());
    }
}
