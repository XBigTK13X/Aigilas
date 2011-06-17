using System.Collections.Generic;
using System.Linq;
using OGUR.Collision;
using OGUR.HUD;
using OGUR.Management;
using OGUR.Creatures;
using OGUR.Sprites;

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

        public static Point FindNearestPlayer(ICreature target)
        {
            var players = GetObjects(CreatureType.PLAYER);
            ICreature closestPlayer = players[0];
            foreach(ICreature player in players)
            {
                List<Point> path = new List<Point>();
                path.Add(new Point((int)target.GetPosition().X / Sprites.SpriteInfo.Width, (int)target.GetPosition().Y / Sprites.SpriteInfo.Height));
                int weight = 0;
                bool searching = true;
                while (searching)
                {
                    weight++;
                    foreach (Point tile in path)
                    {
                        for (int ii = -1; ii <= 1; ii++)
                        {
                            for (int jj = -1; jj <= 1; jj++)
                            {
                                var check = new Point(tile.X + ii*SpriteInfo.Width, tile.Y + jj*SpriteInfo.Height,weight);
                                if (!CoordVerifier.IsBlocked(check.X,check.Y))
                                {
                                    if(path.Where(o=>o.X==check.X && o.Y == check.Y && o.Weight < check.Weight).Count()==0)
                                    {
                                        path.Add(check);
                                        //Check to see if this Point is the player. If so, then stop looking.
                                        // if so then return the first link in the path
                                        return path[path.Count() - 2];
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
    }
}