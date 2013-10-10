package aigilas.gods;

import aigilas.items.ItemClass;
import com.badlogic.gdx.graphics.Color;
import sps.util.Colors;

import java.util.HashMap;

public enum GodId {
    Lust(Colors.rgb(126, 115, 255), ItemClass.Leggings, ItemClass.Melee_Weapon),
    Greed(Colors.rgb(201, 217, 98), ItemClass.Head_Gear, ItemClass.Gloves),
    Sloth(Colors.rgb(119, 230, 218), ItemClass.Shield, ItemClass.Head_Gear),
    Envy(Colors.rgb(39, 140, 39), ItemClass.Torso_Garb, ItemClass.Ranged_Weapon),
    Wrath(Colors.rgb(130, 52, 52), ItemClass.Melee_Weapon, ItemClass.Ranged_Ammo),
    Gluttony(Colors.rgb(176, 104, 37), ItemClass.Gloves, ItemClass.Torso_Garb),
    Pride(Colors.rgb(150, 33, 150), ItemClass.Ring, ItemClass.Feet);

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
