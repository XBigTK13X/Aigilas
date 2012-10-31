package aigilas.energygement;

import sps.io.Buttons;
import sps.io.Contexts;
import sps.io.Keys;

public enum Commands {
    MoveUp(Contexts.Nonfree),
    MoveDown(Contexts.Nonfree),
    MoveLeft(Contexts.Nonfree),
    MoveRight(Contexts.Nonfree),
    Confirm(Contexts.All),
    Inventory(Contexts.All),
    Skill(Contexts.All),
    CycleLeft(Contexts.All),
    CycleRight(Contexts.All),
    Cancel(Contexts.All),
    Start(Contexts.All),
    Back(Contexts.All),
    LockSkill(Contexts.All),
    HotSkill1(Contexts.All),
    HotSkill2(Contexts.All),
    HotSkill3(Contexts.All),
    ToggleDevConsole(Contexts.All);

    private Buttons _button;
    private Keys _key;
    public final Contexts Context;

    private Commands(Contexts context) {
        Context = context;
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

    public static Commands get(String s) {
        for (Commands key : values()) {
            if (key.name().equalsIgnoreCase(s)) {
                return key;
            }
        }
        return null;
    }
}
