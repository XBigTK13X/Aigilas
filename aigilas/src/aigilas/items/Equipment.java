package aigilas.items;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.Stats;

import java.util.HashMap;

public class Equipment {
    private final HashMap<ItemSlot, GenericItem> _slots = new HashMap<ItemSlot, GenericItem>();
    private final BaseCreature _parent;

    public Equipment(BaseCreature owner) {
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
        return _slots.containsKey(itemClass) && (item == _slots.get(itemClass));
    }

    public HashMap<ItemSlot, GenericItem> getItems() {
        return _slots;
    }

    private Stats modifiers = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    public Stats getModifiers() {
        modifiers.zeroOut();
        for (ItemSlot slot : _slots.keySet()) {
            modifiers.add(_slots.get(slot).getModifiers());
        }
        return modifiers;
    }
}
