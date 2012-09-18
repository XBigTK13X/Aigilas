package aigilas.items;

import aigilas.management.SpriteType;

import java.util.Arrays;

public enum ItemName {
    Sword(
            ItemClass.Melee_Weapon,
            new Slots(Arrays.asList(ItemSlot.RIGHT_HAND, ItemSlot.LEFT_HAND))),
    Pants(ItemClass.Leggings, new Slots(Arrays.asList(ItemSlot.LEGS))),
    Dagger(ItemClass.Melee_Weapon, null),
    Shield(ItemClass.Shield, null),
    Bow(ItemClass.Ranged_Weapon, null),
    Arrow(ItemClass.Ranged_Ammo, null),
    Staff(ItemClass.Melee_Weapon, null),
    Hood(ItemClass.Head_Gear, null),
    Shirt(ItemClass.Torso_Garb, null),
    Flak(null, null);

    public final SpriteType Sprite = SpriteType.ITEM;
    public final ItemClass Category;
    public final Slots Slots;

    private ItemName(ItemClass category, Slots slots) {
        Category = category;
        Slots = slots;
    }
}
