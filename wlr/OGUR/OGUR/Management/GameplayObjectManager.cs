using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Factory;
using OGUR.Creatures;

namespace OGUR.Management
{
    static class GameplayObjectManager
    {
        static private List<GameplayObject> m_contents = new List<GameplayObject>();
        
        static public GameplayObject AddObject(GameplayObject gameplayObject)
        {
            gameplayObject.LoadContent();
            m_contents.Add(gameplayObject);
            return gameplayObject;
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

        static public ICreature GetObject(CreatureType type)
        {
            if (m_contents != null)
            {
                foreach (ICreature item in m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE))
                {
                    if (item.GetCreatureType() == type)
                    {
                        return item;
                    }
                }
            }
            return null;
        }

        static public List<ICreature> GetObjects(CreatureType type)
        {
            List<ICreature> result = new List<ICreature>();
            foreach (ICreature item in m_contents.Where(o=>o.GetObjectType()==GameObjectType.CREATURE))
            {
                if (item.GetCreatureType() == type)
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

        static public void RemoveObject(GameplayObject target)
        {
            m_contents.Remove(target);
        }


        static public void Clear()
        {
            m_contents.Clear();
            CreatureFactory.ResetPlayerCount();
        }
        static public void Update()
        {
            for (int ii = 0; ii < m_contents.Count; ii++)
            {
                if (m_contents[ii].GetObjectType() == GameObjectType.CREATURE)
                {
                    (m_contents[ii] as ICreature).Update();
                }
                else
                {
                    m_contents[ii].Update();
                }
                if (!m_contents[ii].IsActive())
                {
                    m_contents.Remove(m_contents[ii]);
                    ii--;
                }
            }
        }
        static public void Draw()
        {
            foreach (GameplayObject component in m_contents)
            {
                if (XnaManager.GetRenderTarget() != null)
                {
                    component.Draw();
                }
            }
        }
        static public void LoadContent()
        {
            if (XnaManager.GetRenderTarget() != null)
            {
                foreach (GameplayObject component in m_contents)
                {
                    component.LoadContent();
                }
            }
        }
    }
}
