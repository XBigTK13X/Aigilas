using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using OGUR.GameObjects;
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

        public Dictionary<GenericItem, int> GetItems(ItemClass iClass)
        {
            return m_contents.Where(element => element.Key.GetItemClass() == iClass).ToDictionary(element => element.Key, element => element.Value);
        }

        public void Remove(GenericItem item)
        {
            if(Contains(item))
            {
                m_contents[item]--;
                if(m_contents[item]<=0)
                {
                    m_contents.Remove(item);
                }
            }
        }

        public bool Contains(GenericItem item)
        {
            return m_contents.ContainsKey(item);
        }

        public void DropAll()
        {
            foreach(var item in m_contents.Keys)
            {
                while(m_contents[item]>0)
                {
                    GameplayObjectManager.AddObject(new GenericItem(item, m_parent.GetPosition().X,
                                                                    m_parent.GetPosition().Y));
                    Remove(item);
                }
            }
        }
    }
}
