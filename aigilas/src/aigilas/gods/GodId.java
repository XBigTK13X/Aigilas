package aigilas.gods;

import aigilas.items.ItemClass;
import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

public enum GodId {
    LUST(Color.MAGENTA, ItemClass.Leggings, ItemClass.Melee_Weapon),
    GREED(Color.YELLOW, ItemClass.Head_Gear, ItemClass.Gloves),
    SLOTH(Color.GRAY, ItemClass.Shield, ItemClass.Head_Gear),
    ENVY(Color.YELLOW, ItemClass.Torso_Garb, ItemClass.Ranged_Weapon),
    WRATH(Color.RED, ItemClass.Melee_Weapon, ItemClass.Ranged_Ammo),
    GLUTTONY(Color.GREEN, ItemClass.Gloves, ItemClass.Torso_Garb),
    PRIDE(Color.CYAN, ItemClass.Ring, ItemClass.Feet);

    public final Color Tint;
    public final ItemClass Desires;
    public final ItemClass Detests;

    private static final HashMap<GodId, God> __instances = new HashMap<GodId,God>();

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
