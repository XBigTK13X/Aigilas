using System.Collections.Generic;
using System.Linq;
using Aigilas.Creatures;
using SPX.Entities;

namespace Aigilas.Items
{
    public class Inventory
    {
        Dictionary<GenericItem,int> _contents = new Dictionary<GenericItem, int>(); 
        private ICreature _parent;

        public Inventory(ICreature parent)
        {
            _parent = parent;
        }
        
        public ICreature GetParent()
        {
            return _parent;
        }

        public GenericItem GetNonZeroEntry()
        {
            return _contents.Keys.Where(k=>_contents[k]>0).FirstOrDefault();
        }

        public void Add(GenericItem item)
        {
            if(Contains(item))
            {
                _contents[item]++;
            }
            else
            {
                _contents.Add(item, 1);
            }
        }

        private readonly Dictionary<GenericItem, int> _itemResult = new Dictionary<GenericItem, int>();
        public Dictionary<GenericItem, int> GetItems(int iClass)
        {
            _itemResult.Clear();
            foreach (var key in _contents.Keys)
            {
                if (key.GetItemClass() == iClass)
                {
                    _itemResult.Add(key, _contents[key]);
                }
            }
            return _itemResult;
        }

        public void Remove(GenericItem item)
        {
            if(Contains(item))
            {
                _contents[item]--;
                if(_contents[item]<=-1)
                {
                    _contents.Remove(item);
                }
            }
        }

        public bool Contains(GenericItem item)
        {
            return _contents.ContainsKey(item);
        }

        public int GetItemCount(GenericItem item)
        {
            if(_contents.Keys.Contains(item))
            {
                return _contents[item];
            }
            return 0;
        }

        public void DropAll()
        {
            foreach(var item in _contents.Keys)
            {
                while(_contents[item]>0)
                {
                    EntityManager.AddObject(new GenericItem(item,_parent.GetLocation()));
                    Remove(item);
                }
            }
        }

        float _nonZeroResult;
        public float NonZeroCount()
        {
            _nonZeroResult = 0;
            foreach(var item in _contents.Where(c=>c.Value>0))
            {
                _nonZeroResult += item.Value;
            }
            return _nonZeroResult;
        }
    }
}
