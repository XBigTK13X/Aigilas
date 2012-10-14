package aigilas.gods;

import aigilas.items.ItemClass;
import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

public enum GodId {
    Lust(Color.MAGENTA, ItemClass.Leggings, ItemClass.Melee_Weapon),
    Greed(Color.YELLOW, ItemClass.Head_Gear, ItemClass.Gloves),
    Sloth(Color.GRAY, ItemClass.Shield, ItemClass.Head_Gear),
    Envy(Color.YELLOW, ItemClass.Torso_Garb, ItemClass.Ranged_Weapon),
    Wrath(Color.RED, ItemClass.Melee_Weapon, ItemClass.Ranged_Ammo),
    Gluttony(Color.GREEN, ItemClass.Gloves, ItemClass.Torso_Garb),
    Pride(Color.CYAN, ItemClass.Ring, ItemClass.Feet);

    public final Color Tint;
    public final ItemClass Desires;
    public final ItemClass Detests;

    private static final HashMap<GodId, God> __instances = new HashMap<GodId, God>();

    private GodId(Color tint, ItemClass desires, ItemClass detests) {
        Tint = tint;
        Desires = desires;
        Detests = detests;
    }

    public God getInstance() {
        if (!__instances.containsKey(this)) {
            __instances.put(this, new God(this));
        }
        return __instances.get(this);
    }
}
