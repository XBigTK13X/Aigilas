using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using OGUR.Collision;
using OGUR.Dungeons;
using OGUR.Gods;
using OGUR.Management;
using OGUR.Creatures;
using OGUR.Sprites;
using OGUR.States;
using Microsoft.Xna.Framework.Graphics;

namespace OGUR.GameObjects
{
    public static class GameplayObjectManager
    {
        private static List<GameplayObject> m_contents = new List<GameplayObject>();
        private static Dictionary<Point2,List<GameplayObject>> m_gridContents = new Dictionary<Point2, List<GameplayObject>>();

        public static GameplayObject AddObject(GameplayObject gameplayObject)
        {
            gameplayObject.LoadContent();
            m_contents.Add(gameplayObject);
            AddToGrid(gameplayObject);
            return gameplayObject;
        }

        //GOT Accessors
        public static GameplayObject GetObject(GameObjectType type)
        {
            if (m_contents != null)
            {
                return m_contents.FirstOrDefault(item => item.GetObjectType() == type);
            }
            return null;
        }

        public static IEnumerable<GameplayObject> GetObjects(GameObjectType type,Point2 target)
        {
            if (m_contents != null)
            {
                return GetObjects(type).Where(o=>o.Contains(target));
            }
            return null;
        }

        public static IEnumerable<GameplayObject> GetObjects(GameObjectType type)
        {
            return m_contents.Where(item => item.GetObjectType() == type);
        }

        //CT Accessors
        public static ICreature GetObject(CreatureType type)
        {
            return m_contents != null ? m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().FirstOrDefault(creature => creature.GetCreatureType() == type) : null;
        }

        private static List<ICreature> creatures = new List<ICreature>();
        public static IEnumerable<ICreature> GetObjects(CreatureType type)
        {
            creatures.Clear();
            if (type != CreatureType.NONPLAYER)
            {
                foreach (var elem in m_contents)
                {
                    if (elem.GetObjectType() == GameObjectType.CREATURE)
                    {
                        if (((ICreature)elem).GetCreatureType() == type)
                        {
                            creatures.Add(((ICreature)elem));
                        }
                    }
                }

            }
            else
            {
                if (type != CreatureType.NONPLAYER)
                {
                    foreach (var elem in m_contents)
                    {
                        if (elem.GetObjectType() == GameObjectType.CREATURE)
                        {
                            if (((ICreature)elem).GetCreatureType() != CreatureType.PLAYER)
                            {
                                creatures.Add(((ICreature)elem));
                            }
                        }
                    }

                }
            }
            return creatures;
        }

        public static bool IsLocationBlocked(Point2 location)
        {
            foreach(GameplayObject elem in m_gridContents[location])
            {
                if(elem.IsBlocking())
                {
                    return true;
                }
            }
            return false;
        }
        public static IEnumerable<GameplayObject> GetObjectsToCache()
        {
            return m_contents.Where(o => o.GetObjectType() != GameObjectType.FLOOR);
        }

        public static GameplayObject GetNearestPlayer(GameplayObject target)
        {
            GameplayObject closest = GetObjects(CreatureType.PLAYER).FirstOrDefault();
            foreach (var player in GetObjects(CreatureType.PLAYER))
            {
                if (HitTest.GetDistanceSquare(target, player) < HitTest.GetDistanceSquare(target, closest))
                {
                    closest = player;
                }
            }
            return closest;
        }

        public static ICreature GetNearestPlayer(ICreature target)
        {
            return (ICreature)GetNearestPlayer((GameplayObject)target);
        }

        public static void AddObjects(IEnumerable<GameplayObject> cache)
        {
            m_contents.AddRange(cache);
        }

        private static GameplayObject s_nearest;
        public static Player GetTouchingPlayer(GameplayObject source)
        {
            s_nearest = GetNearestPlayer(source);
            if (HitTest.IsTouching(source, s_nearest))
            {
                return (Player)s_nearest;
            }
            return null;
        }

        public static bool AnyContains(Point2 target, GameObjectType type)
        {
            return m_gridContents[target].Any(o => o.GetObjectType() == type);
        }

        public static void RemoveObject(GameplayObject target)
        {
            m_contents.Remove(target);
        }

        public static void Clear()
        {
            m_contents = new List<GameplayObject>();
            m_gridContents = new Dictionary<Point2, List<GameplayObject>>();
            CreatureFactory.ResetPlayerCount();
        }

        public static void Reset()
        {
            m_contents = new List<GameplayObject>();
            m_gridContents = new Dictionary<Point2, List<GameplayObject>>();
            CreatureFactory.ResetPlayerCount();
            DungeonFactory.Start();
            LoadContent();
            Draw();
        }

        private static GameplayObject m_updateTarget;
        public static void Update()
        {
            if(!GetObjects(CreatureType.PLAYER).Any(o=>o.IsActive()))
            {
                Reset();
                StateManager.LoadState(new GameOverState());
            }
            for (var ii = 0; ii < m_contents.Count; ii++)
            {
                if(ii>=m_contents.Count)
                {
                    return;
                }
                if (!m_contents[ii].IsActive())
                {
                    m_gridContents[m_contents[ii].GetLocation()].Remove(m_contents[ii]);
                    m_contents.Remove(m_contents[ii]);
                    ii--;
                    continue;
                }                
                if (m_contents[ii].GetObjectType() == GameObjectType.CREATURE)
                {
                    m_updateTarget = m_contents[ii] as ICreature;
                    if (m_updateTarget != null) m_updateTarget.Update();
                }
                else
                {
                    m_contents[ii].Update();
                }
            }
        }

        public static void Draw()
        {
            if (XnaManager.Renderer != null)
            {
                foreach (var component in m_contents)
                {

                    component.Draw();
                }
            }
        }

        public static void LoadContent()
        {
            if (XnaManager.Renderer != null)
            {
                foreach (var component in m_contents)
                {
                    component.LoadContent();
                }
            }
        }

        private static void AddToGrid(GameplayObject gameplayObject)
        {
            if (!m_gridContents.ContainsKey(gameplayObject.GetLocation()))
            {
                m_gridContents.Add(gameplayObject.GetLocation(), new List<GameplayObject>() { gameplayObject });
            }
            else
            {
                m_gridContents[gameplayObject.GetLocation()].Add(gameplayObject);
            }
        }

        public static void UpdateGridLocation(GameplayObject gameplayObject, Point2 oldLocation)
        {
            if(m_gridContents!=null && oldLocation !=null)
            {
                m_gridContents[oldLocation].Remove(gameplayObject);
                AddToGrid(gameplayObject);
            }
        }
    }
}