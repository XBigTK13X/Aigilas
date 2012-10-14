package aigilas.items;

public enum ItemClass {
    Melee_Weapon(ItemSlot.Right_Hand),
    Ranged_Weapon(ItemSlot.Right_Hand),
    Ranged_Ammo(ItemSlot.Left_Hand),
    Ring(ItemSlot.Left_Finger),
    Leggings(ItemSlot.Legs),
    Torso_Garb(ItemSlot.Torso),
    Feet(ItemSlot.Feet),
    Head_Gear(ItemSlot.Head),
    Gloves(ItemSlot.Hands),
    Shield(ItemSlot.Left_Hand);

    public final ItemSlot Slot;

    private ItemClass(ItemSlot slot) {
        Slot = slot;
    }

    public String hudName() {
        return toString().replace("_", " ");
    }
}
