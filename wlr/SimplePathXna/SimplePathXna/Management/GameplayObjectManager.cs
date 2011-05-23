using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.GameObjects;
using welikerogues.Sprites;

namespace welikerogues.Management
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
