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

        public static GameplayObject GetObject(int type)
        {
            if (m_contents != null)
            {
                return m_contents.FirstOrDefault(item => item.GetObjectType() == type);
            }
            return null;
        }

        private static readonly List<GameplayObject> gopResults = new List<GameplayObject>();
        public static List<GameplayObject> GetObjects(int type,Point2 target)
        {
            if (m_contents != null)
            {
                goResults.Clear();
                goResults.AddRange(GetObjects(type));
                gopResults.Clear();
                for (int ii = 0; ii < goResults.Count(); ii++)
                {
                    if (goResults[ii].Contains(target))
                    {
                        gopResults.Add(goResults[ii]);
                    }
                }
                return gopResults;
            }
            return null;
        }

        private static readonly List<GameplayObject> goResults = new List<GameplayObject>();
        public static List<GameplayObject> GetObjects(int type)
        {
            //return m_contents.Where(item => item.GetObjectType() == type);
            goResults.Clear();
            for (int ii = 0; ii < m_contents.Count(); ii++)
            {
                if (m_contents[ii].GetObjectType() == type)
                {
                    goResults.Add(m_contents[ii]);
                }
            }
            return goResults;
        }

        //CT Accessors
        public static ICreature GetCreature(int type)
        {
            return m_contents != null ? m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().FirstOrDefault(creature => creature.GetCreatureType() == type) : null;
        }

        private static List<ICreature> creatures = new List<ICreature>();
        public static List<ICreature> GetCreatures(int type)
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
            return creatures;
        }
        public static List<ICreature> GetCreaturesAt(Point2 target)
        {
            creatures.Clear();
            foreach (var elem in m_gridContents[target])
            {
                if (elem.GetObjectType() == GameObjectType.CREATURE)
                {
                    creatures.Add(((ICreature)elem));
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
            GameplayObject closest = GetCreatures(CreatureType.PLAYER).FirstOrDefault();
            foreach (var player in GetCreatures(CreatureType.PLAYER))
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

        private static Player s_nearest;
        public static Player GetTouchingPlayer(GameplayObject source)
        {
            s_nearest = null;
            foreach (var elem in m_gridContents[source.GetLocation()])
            {
                if (elem.GetObjectType() == GameObjectType.CREATURE)
                {
                    if (((ICreature)elem).GetCreatureType() == CreatureType.PLAYER)
                    {
                        s_nearest = (Player)elem;
                    }
                }
            }
            return s_nearest;
        }

        public static bool AnyContains(Point2 target, int type)
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
            creatures.Clear();
            CreatureFactory.ResetPlayerCount();
            DungeonFactory.Start();
            LoadContent();
        }

        public static void Update()
        {   
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
                m_contents[ii].Update();
            }
            if (!(GetCreatures(CreatureType.PLAYER).Count() > 0))
            {
                Reset();
                StateManager.LoadState(new GameOverState());
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