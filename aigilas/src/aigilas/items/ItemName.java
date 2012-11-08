package aigilas.items;

import aigilas.Common;
import sps.bridge.SpriteType;
import sps.bridge.SpriteTypes;

import java.util.Arrays;

public enum ItemName {
    Sword(
            ItemClass.Melee_Weapon,
            new Slots(Arrays.asList(ItemSlot.Right_Hand, ItemSlot.Left_Hand))),
    Pants(ItemClass.Leggings, new Slots(Arrays.asList(ItemSlot.Legs))),
    Dagger(ItemClass.Melee_Weapon, null),
    Shield(ItemClass.Shield, null),
    Bow(ItemClass.Ranged_Weapon, null),
    Arrow(ItemClass.Ranged_Ammo, null),
    Staff(ItemClass.Melee_Weapon, null),
    Hood(ItemClass.Head_Gear, null),
    Shirt(ItemClass.Torso_Garb, null),
    Flak(null, null);

    public final SpriteType Sprite;
    public final ItemClass Category;
    public final Slots Slots;

    private ItemName(ItemClass category, Slots slots) {
        Category = category;
        Slots = slots;
        Sprite = SpriteTypes.get(Common.Item);
    }
}
