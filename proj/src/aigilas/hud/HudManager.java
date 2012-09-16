package aigilas.hud;

import aigilas.creatures.ICreature;
import aigilas.items.Equipment;
import aigilas.items.Inventory;

public class HudManager {
    private InventoryHud _inventory;
    private SkillHud _skill;

    public HudManager(ICreature parent, Inventory inventory, Equipment equipment) {
        _inventory = new InventoryHud(parent, inventory, equipment);
        _skill = new SkillHud(parent);
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
