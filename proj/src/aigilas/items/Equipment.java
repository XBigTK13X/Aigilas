package aigilas.items;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;

import java.util.HashMap;

public class Equipment {
    private HashMap<ItemSlot, GenericItem> _slots = new HashMap<>();
    private ICreature _parent;

    public Equipment(ICreature owner) {
        _parent = owner;
    }

    public void unequip(GenericItem item) {
        if (isRegistered(item)) {
            unregister(item);
        }
    }

    public void register(GenericItem item) {
        ItemSlot itemSlot = item.getItemClass().Slot;
        if (_slots.containsKey(itemSlot)) {
            unequip(_slots.get(itemSlot));
        }
        _slots.put(itemSlot, item);
    }

    public void unregister(GenericItem item) {
        ItemSlot itemSlot = item.getItemClass().Slot;
        if (_slots.containsKey(itemSlot)) {
            _parent.pickupItem(_slots.get(itemSlot));
            _slots.remove(itemSlot);
        }
    }

    public boolean isRegistered(GenericItem item) {
        ItemSlot itemClass = item.getItemClass().Slot;
        if (_slots.containsKey(itemClass)) {
            return (item == _slots.get(itemClass));
        }
        return false;
    }

    private float bonusSum;

    public float calculateBonus(StatType stat) {
        bonusSum = 0;
        for (ItemSlot slot : _slots.keySet()) {
            bonusSum += _slots.get(slot).getStatBonus(stat);
        }
        return bonusSum;
    }

    public HashMap<ItemSlot, GenericItem> getItems() {
        return _slots;
    }
}
