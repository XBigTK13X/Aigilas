using System.Collections.Generic;
using System.Linq;
using OGUR.Collision;
using OGUR.HUD;
using OGUR.Management;
using OGUR.Creatures;

namespace OGUR.GameObjects
{
    internal static class GameplayObjectManager
    {
        private static readonly List<GameplayObject> m_contents = new List<GameplayObject>();

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
                return m_contents.FirstOrDefault(item => item.GetObjectType() == type);
            }
            return null;
        }

        public static List<GameplayObject> GetObjects(GameObjectType type,Point target)
        {
            if (m_contents != null)
            {
                return m_contents.Where(o => o.GetObjectType() == type && o.Contains(target)).ToList();
            }
            return null;
        }

        public static List<GameplayObject> GetObjects(GameObjectType type)
        {
            return m_contents.Where(item => item.GetObjectType() == type).ToList();
        }

        public static ICreature GetObject(CreatureType type)
        {
            return m_contents != null ? m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().FirstOrDefault(creature => creature.GetCreatureType() == type) : null;
        }

        public static List<ICreature> GetObjects(CreatureType type)
        {
            return m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().Where(item => item.GetCreatureType() == type).ToList();
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
                    var creature = m_contents[ii] as ICreature;
                    if (creature != null) creature.Update();
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
            InventoryScreensManager.Update();
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
            InventoryScreensManager.Draw();
        }

        public static void LoadContent()
        {
            if (XnaManager.GetRenderTarget() != null)
            {
                foreach (GameplayObject component in m_contents)
                {
                    component.LoadContent();
                }
                InventoryScreensManager.LoadContent();
            }
        }
    }
}