using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using SPX.Core;
using Microsoft.Xna.Framework.Graphics;

namespace SPX.Entities
{
    public static class EntityManager
    {
        private static List<IEntity> m_contents = new List<IEntity>();
        private static Dictionary<Point2,List<IEntity>> m_gridContents = new Dictionary<Point2, List<IEntity>>();

        public static IEntity AddObject(IEntity Entity)
        {
            Entity.LoadContent();
            m_contents.Add(Entity);
            AddToGrid(Entity);
            return Entity;
        }

        public static IEntity GetObject(int type)
        {
            if (m_contents != null)
            {
                return m_contents.FirstOrDefault(item => item.EntityType() == type);
            }
            return null;
        }

        private static readonly List<IEntity> gopResults = new List<IEntity>();
        public static List<IEntity> GetEntities(int type,Point2 target)
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

        private static readonly List<IEntity> goResults = new List<IEntity>();
        public static List<IEntity> GetObjects(int type)
        {
            goResults.Clear();
            for (int ii = 0; ii < m_contents.Count(); ii++)
            {
                if (m_contents[ii].EntityType() == type)
                {
                    goResults.Add(m_contents[ii]);
                }
            }
            return goResults;
        }

        //CT Accessors
        public static IActor GetActor(int type)
        {
            return m_contents != null ? m_contents.Where(o => o.EntityType() == EntityType.ACTOR).Cast<IActor>().FirstOrDefault(creature => creature.ActorType() == type) : null;
        }

        private static List<IActor> creatures = new List<IActor>();
        public static List<IActor> GetActors(int type)
        {
            creatures.Clear();
            if (type != ActorType.NONPLAYER)
            {
                foreach (var elem in m_contents)
                {
                    if (elem.EntityType() == EntityType.ACTOR)
                    {
                        if (((IActor)elem).ActorType() == type)
                        {
                            creatures.Add(((IActor)elem));
                        }
                    }
                }
            }
            else
            {
                foreach (var elem in m_contents)
                {
                    if (elem.EntityType() == EntityType.ACTOR)
                    {
                        if (((IActor)elem).ActorType() != ActorType.PLAYER)
                        {
                            creatures.Add(((IActor)elem));
                        }
                    }
                }
            }
            return creatures;
        }
        
        public static List<IActor> GetActorsAt(Point2 target)
        {
            creatures.Clear();
            foreach (var elem in m_gridContents[target])
            {
                if (elem.EntityType() == EntityType.ACTOR)
                {
                    creatures.Add(((IActor)elem));
                }
            }
            return creatures;
        }

        public static bool IsLocationBlocked(Point2 location)
        {
            foreach(IEntity elem in m_gridContents[location])
            {
                if(elem.IsBlocking())
                {
                    return true;
                }
            }
            return false;
        }
        public static IEnumerable<IEntity> GetObjectsToCache()
        {
            return m_contents.Where(o => o.EntityType() != EntityType.FLOOR);
        }

        public static IActor GetNearestPlayer(IEntity target)
        {
            var closest = GetActors(ActorType.PLAYER).FirstOrDefault() as IEntity;
            foreach (var player in GetActors(ActorType.PLAYER).Select(x=>x as IEntity))
            {
                if (HitTest.GetDistanceSquare(target, player) < HitTest.GetDistanceSquare(target, closest))
                {
                    closest = player;
                }
            }
            return closest as IActor;
        }

        public static IActor GetNearestPlayer(IActor target)
        {
            return (IActor)GetNearestPlayer((IEntity)target);
        }

        public static void AddObjects(IEnumerable<IEntity> cache)
        {
            m_contents.AddRange(cache);
        }

        public static bool AnyContains(Point2 target, int type)
        {
            return m_gridContents[target].Any(o => o.EntityType() == type);
        }

        public static void RemoveObject(IEntity target)
        {
            m_contents.Remove(target);
        }

        public static void Clear()
        {
            m_contents = new List<IEntity>();
            m_gridContents = new Dictionary<Point2, List<IEntity>>();
        }

        public static void Reset()
        {
            m_contents = new List<IEntity>();
            m_gridContents = new Dictionary<Point2, List<IEntity>>();
            creatures.Clear();
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

        private static void AddToGrid(IEntity Entity)
        {
            if (!m_gridContents.ContainsKey(Entity.GetLocation()))
            {
                m_gridContents.Add(Entity.GetLocation(), new List<IEntity>() { Entity });
            }
            else
            {
                m_gridContents[Entity.GetLocation()].Add(Entity);
            }
        }

        public static void UpdateGridLocation(IEntity Entity, Point2 oldLocation)
        {
            if(m_gridContents!=null && oldLocation !=null)
            {
                m_gridContents[oldLocation].Remove(Entity);
                AddToGrid(Entity);
            }
        }

        private static List<IActor> _players = new List<IActor>();
        public static IEnumerable<IActor> GetPlayers()
        {
            _players.Clear();
            foreach (var tile in m_contents)
            {
                if (tile.EntityType() == EntityType.ACTOR && (tile as IActor).ActorType() == ActorType.PLAYER)
                {
                    _players.Add((tile as IActor));
                }
            }
            return _players;
        }

        //Deprecated methods
        public static ICollection<IEntity> GetEntitiesToCache()
        {
            return m_contents.Where(c => c.EntityType() != EntityType.FLOOR).ToList();
        }
        public static IActor GetTouchingCreature(IEntity entity)
        {
            return m_contents.Where(c=>c.EntityType() == EntityType.ACTOR).SingleOrDefault(c => c.Contains(entity.GetLocation())) as IActor;
        }
    }
}