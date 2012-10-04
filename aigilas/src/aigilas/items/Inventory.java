package aigilas.items;

import aigilas.creatures.BaseCreature;
import sps.entities.EntityManager;

import java.util.HashMap;

public class Inventory {
    final HashMap<GenericItem, Integer> _contents = new HashMap<GenericItem,Integer>();
    private final BaseCreature _parent;

    public Inventory(BaseCreature parent) {
        _parent = parent;
    }

    public BaseCreature getParent() {
        return _parent;
    }

    public GenericItem getNonZeroEntry() {
        for (GenericItem key : _contents.keySet()) {
            if (_contents.get(key) > 0) {
                return key;
            }
        }
        return null;
    }

    public void add(GenericItem item) {
        if (contains(item)) {
            _contents.put(item, _contents.get(item) + 1);
        } else {
            _contents.put(item, 1);
        }
    }

    private final HashMap<GenericItem, Integer> _itemResult = new HashMap<GenericItem,Integer>();

    public HashMap<GenericItem, Integer> getItems(ItemClass iClass) {
        _itemResult.clear();
        for (GenericItem key : _contents.keySet()) {
            if (key.getItemClass() == iClass) {
                _itemResult.put(key, _contents.get(key));
            }
        }
        return _itemResult;
    }

    public void remove(GenericItem item) {
        if (contains(item)) {
            _contents.put(item, _contents.get(item) - 1);
            if (_contents.get(item) <= -1) {
                _contents.remove(item);
            }
        }
    }

    public boolean contains(GenericItem item) {
        return _contents.containsKey(item);
    }

    public int getItemCount(GenericItem item) {
        if (_contents.keySet().contains(item)) {
            return _contents.get(item);
        }
        return 0;
    }

    public void dropAll() {
        for (GenericItem item : _contents.keySet()) {
            while (_contents.get(item) > 0) {
                EntityManager.get().addObject(new GenericItem(item, _parent.getLocation()));
                remove(item);
            }
        }
    }

    float _nonZeroResult;

    public float nonZeroCount() {
        _nonZeroResult = 0;
        for (GenericItem item : _contents.keySet()) {
            if (_contents.get(item) > 0) {
                _nonZeroResult += _contents.get(item);
            }
        }
        return _nonZeroResult;
    }
}
