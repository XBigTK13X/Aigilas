package aigilas.hud;

import aigilas.creatures.BaseCreature;
import aigilas.items.Equipment;
import aigilas.items.Inventory;

public class HudContainer {
    private final InventoryHud _inventory;
    private final SkillHud _skill;

    public HudContainer(BaseCreature parent, Inventory inventory, Equipment equipment) {
        _inventory = new InventoryHud(parent, inventory, equipment);
        _skill = new SkillHud(parent);
        HudRenderer.get().register(_inventory);
        HudRenderer.get().register(_skill);
        _skill.toggle();
    }

    public boolean toggleInventory() {
        _inventory.toggle();
        _skill.toggle();
        return _inventory.isVisible();
    }

    public void update() {
        _inventory.update();
        _skill.update();
    }

    public void draw() {
        _inventory.draw();
        _skill.draw();
    }
}
