package aigilas.items;

import java.util.HashMap;

import spx.entities.EntityManager;
import aigilas.creatures.ICreature;

public class Inventory {
	HashMap<GenericItem, Integer> _contents = new HashMap<GenericItem, Integer>();
	private ICreature _parent;

	public Inventory(ICreature parent) {
		_parent = parent;
	}

	public ICreature GetParent() {
		return _parent;
	}

	public GenericItem GetNonZeroEntry() {
		for (GenericItem key : _contents.keySet()) {
			if (_contents.get(key) > 0) {
				return key;
			}
		}
		return null;
	}

	public void Add(GenericItem item) {
		if (Contains(item)) {
			_contents.put(item, _contents.get(item) + 1);
		}
		else {
			_contents.put(item, 1);
		}
	}

	private HashMap<GenericItem, Integer> _itemResult = new HashMap<GenericItem, Integer>();

	public HashMap<GenericItem, Integer> GetItems(ItemClass iClass) {
		_itemResult.clear();
		for (GenericItem key : _contents.keySet()) {
			if (key.GetItemClass() == iClass) {
				_itemResult.put(key, _contents.get(key));
			}
		}
		return _itemResult;
	}

	public void Remove(GenericItem item) {
		if (Contains(item)) {
			_contents.put(item, _contents.get(item) - 1);
			if (_contents.get(item) <= -1) {
				_contents.remove(item);
			}
		}
	}

	public boolean Contains(GenericItem item) {
		return _contents.containsKey(item);
	}

	public int GetItemCount(GenericItem item) {
		if (_contents.keySet().contains(item)) {
			return _contents.get(item);
		}
		return 0;
	}

	public void DropAll() {
		for (GenericItem item : _contents.keySet()) {
			while (_contents.get(item) > 0) {
				EntityManager.addObject(new GenericItem(item, _parent.GetLocation()));
				Remove(item);
			}
		}
	}

	float _nonZeroResult;

	public float NonZeroCount() {
		_nonZeroResult = 0;
		for (GenericItem item : _contents.keySet()) {
			if (_contents.get(item) > 0) {
				_nonZeroResult += _contents.get(item);
			}
		}
		return _nonZeroResult;
	}
}
