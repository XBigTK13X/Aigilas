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
    internal static class GameplayObjectManager
    {
        private static List<GameplayObject> m_contents = new List<GameplayObject>();

        public static GameplayObject AddObject(GameplayObject gameplayObject)
        {
            gameplayObject.LoadContent();
            m_contents.Add(gameplayObject);
            return gameplayObject;
        }

        public static GameplayObject GetObject(GameObjectType type)
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

        public static List<GameplayObject> GetObjects(GameObjectType type)
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

        public static ICreature GetObject(CreatureType type)
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

        public static List<ICreature> GetObjects(CreatureType type)
        {
            List<ICreature> result = new List<ICreature>();
            foreach (ICreature item in m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE))
            {
                if (item.GetCreatureType() == type)
                {
                    result.Add(item);
                }
            }
            return result;
        }


        public static List<GameplayObject> GetObjects()
        {
            return m_contents;
        }

        public static void RemoveObject(GameplayObject target)
        {
            m_contents.Remove(target);
        }


        public static void Clear()
        {
            m_contents.Clear();
            CreatureFactory.ResetPlayerCount();
        }

        public static void Update()
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

        public static void Draw()
        {
            foreach (GameplayObject component in m_contents)
            {
                if (XnaManager.GetRenderTarget() != null)
                {
                    component.Draw();
                }
            }
        }

        public static void LoadContent()
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