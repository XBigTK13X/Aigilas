using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;

namespace OGUR.Management
{
    static class GameplayObjectManager
    {
        static private List<GameplayObject> m_contents = new List<GameplayObject>();
        static public void AddObject(GameplayObject gameplayObject)
        {
            m_contents.Add(gameplayObject);
        }
        static public GameplayObject GetObject(GameObjectType type)
        {
            if (m_contents != null)
            {
                foreach (GameplayObject item in m_contents)
                {
                    if (item.GetObjectType() == type)
                    {
                        return item;
                    }
                }
            }
            return null;
        }
        static public void RemoveObject(GameplayObject target)
        {
            m_contents.Remove(target);
        }
        static public List<GameplayObject> GetObjects(GameObjectType type)
        {
            List<GameplayObject> result = new List<GameplayObject>();
            foreach (GameplayObject item in m_contents)
            {
                if (item.GetObjectType() == type)
                {
                    result.Add(item);
                }
            }
            return result;
        }
        static public List<GameplayObject> GetObjects()
        {
            return m_contents;
        }
        static public void Clear()
        {
            m_contents.Clear();
        }
    }
}
