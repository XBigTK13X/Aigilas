using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.Entities;
using OGUR.Items;
using OGUR.Management;
using OGUR.Text;

namespace OGUR.Items
{
    public class Inventory
    {
        Dictionary<GenericItem,int> m_contents = new Dictionary<GenericItem, int>(); 
        private ICreature m_parent;

        public Inventory(ICreature parent)
        {
            m_parent = parent;
        }
        
        public ICreature GetParent()
        {
            return m_parent;
        }

        public GenericItem GetNonZeroEntry()
        {
            return m_contents.Keys.Where(k=>m_contents[k]>0).FirstOrDefault();
        }

        public void Add(GenericItem item)
        {
            if(Contains(item))
            {
                m_contents[item]++;
            }
            else
            {
                m_contents.Add(item, 1);
            }
        }

        private readonly Dictionary<GenericItem, int> m_itemResult = new Dictionary<GenericItem, int>();
        public Dictionary<GenericItem, int> GetItems(int iClass)
        {
            m_itemResult.Clear();
            foreach (var key in m_contents.Keys)
            {
                if (key.GetItemClass() == iClass)
                {
                    m_itemResult.Add(key, m_contents[key]);
                }
            }
            //return m_contents.Where(element => element.Key.GetItemClass() == iClass).ToDictionary(element => element.Key, element => element.Value);
            return m_itemResult;
        }

        public void Remove(GenericItem item)
        {
            if(Contains(item))
            {
                m_contents[item]--;
                if(m_contents[item]<=-1)
                {
                    m_contents.Remove(item);
                }
            }
        }

        public bool Contains(GenericItem item)
        {
            return m_contents.ContainsKey(item);
        }

        public int GetItemCount(GenericItem item)
        {
            if(m_contents.Keys.Contains(item))
            {
                return m_contents[item];
            }
            return 0;
        }

        public void DropAll()
        {
            foreach(var item in m_contents.Keys)
            {
                while(m_contents[item]>0)
                {
                    EntityManager.AddObject(new GenericItem(item,m_parent.GetLocation()));
                    Remove(item);
                }
            }
        }

        float _nonZeroResult;
        public float NonZeroCount()
        {
            _nonZeroResult = 0;
            foreach(var item in m_contents.Where(c=>c.Value>0))
            {
                _nonZeroResult += item.Value;
            }
            return _nonZeroResult;
        }
    }
}
