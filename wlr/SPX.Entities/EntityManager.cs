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
        private static List<Entity> _contents = new List<Entity>();
        private static Dictionary<Point2,List<Entity>> _gridContents = new Dictionary<Point2, List<Entity>>();

        public static Entity AddObject(Entity Entity)
        {
            Entity.LoadContent();
            _contents.Add(Entity);
            AddToGrid(Entity);
            return Entity;
        }

        public static Entity GetObject(int type)
        {
            if (_contents != null)
            {
                return _contents.FirstOrDefault(item => item.GetEntityType() == type);
            }
            return null;
        }

        private static readonly List<Entity> gopResults = new List<Entity>();
        public static List<Entity> GetEntities(int type,Point2 target)
        {
            if (_contents != null)
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

        private static readonly List<Entity> goResults = new List<Entity>();
        public static List<Entity> GetObjects(int type)
        {
            goResults.Clear();
            for (int ii = 0; ii < _contents.Count(); ii++)
            {
                if (_contents[ii].GetEntityType() == type)
                {
                    goResults.Add(_contents[ii]);
                }
            }
            return goResults;
        }

        //CT Accessors
        public static IActor GetActor(int type)
        {
            return _contents != null ? _contents.Where(o => o.GetEntityType() == EntityType.ACTOR).Cast<IActor>().FirstOrDefault(creature => creature.GetActorType() == type) : null;
        }

        private static List<IActor> creatures = new List<IActor>();
        public static List<IActor> GetActors(int type)
        {
            creatures.Clear();
            if (type != ActorType.NONPLAYER)
            {
                foreach (var elem in _contents)
                {
                    if (elem.GetEntityType() == EntityType.ACTOR)
                    {
                        if (((IActor)elem).GetActorType() == type)
                        {
                            creatures.Add(((IActor)elem));
                        }
                    }
                }
            }
            else
            {
                foreach (var elem in _contents)
                {
                    if (elem.GetEntityType() == EntityType.ACTOR)
                    {
                        if (((IActor)elem).GetActorType() != ActorType.PLAYER)
                        {
                            creatures.Add(((IActor)elem));
                        }
                    }
                }
            }
            return creatures;
        }

        private static IActor _nextResult;
        public static List<IActor> GetActorsAt(Point2 target,int actorType = -1)
        {
            creatures.Clear();
            foreach (var elem in _gridContents[target])
            {
                if (elem.GetEntityType() == EntityType.ACTOR)
                {
                    _nextResult = (IActor)elem;
                    if (actorType == -1 
                        || _nextResult.GetActorType() == actorType
                        || (actorType == ActorType.NONPLAYER && _nextResult.GetActorType() != ActorType.PLAYER))
                    {
                        creatures.Add(_nextResult);
                    }
                }
            }
            return creatures;
        }

        private static List<IActor> creatures2 = new List<IActor>();
        public static List<IActor> GetActorsSurrounding(Point2 target,int distance)
        {
            creatures2.Clear();
            for (int ii = -distance; ii < distance+1; ii++)
            {
                for (int jj = -distance; jj < distance+1; jj++)
                {
                    if (ii != 0 || jj != 0)
                    {
                        foreach (var creature in GetActorsAt(target.Add(new Point2(ii, jj))))
                        {
                            creatures2.Add(creature);
                        }
                    }
                }
            }
            return creatures2;
        }

        public static bool IsLocationBlocked(Point2 location)
        {
            foreach(Entity elem in _gridContents[location])
            {
                if(elem.IsBlocking())
                {
                    return true;
                }
            }
            return false;
        }
        public static IEnumerable<Entity> GetObjectsToCache()
        {
            return _contents.Where(o => o.GetEntityType() != EntityType.FLOOR);
        }

        public static IActor GetNearestPlayer(Entity target)
        {
            var closest = GetActors(ActorType.PLAYER).FirstOrDefault() as Entity;
            foreach (var player in GetActors(ActorType.PLAYER).Select(x=>x as Entity))
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
            return (IActor)GetNearestPlayer((Entity)target);
        }

        public static void AddObjects(IEnumerable<Entity> cache)
        {
            _contents.AddRange(cache);
        }

        public static bool AnyContains(Point2 target, int type)
        {
            return _gridContents[target].Any(o => o.GetEntityType() == type);
        }

        public static void RemoveObject(Entity target)
        {
            _contents.Remove(target);
        }

        public static void Clear()
        {
            _contents = new List<Entity>();
            _gridContents = new Dictionary<Point2, List<Entity>>();
        }

        public static void Reset()
        {
            _contents = new List<Entity>();
            _gridContents = new Dictionary<Point2, List<Entity>>();
            creatures.Clear();
            LoadContent();
        }

        public static void Update()
        {   
            for (var ii = 0; ii < _contents.Count; ii++)
            {
                if(ii>=_contents.Count)
                {
                    return;
                }
                if (!_contents[ii].IsActive())
                {
                    _gridContents[_contents[ii].GetLocation()].Remove(_contents[ii]);
                    _contents.Remove(_contents[ii]);
                    ii--;
                    continue;
                }                
                _contents[ii].Update();
            }
        }

        public static void Draw()
        {
            if (XnaManager.Renderer != null)
            {
                foreach (var component in _contents)
                {
                    component.Draw();
                }
            }
        }

        public static void LoadContent()
        {
            if (XnaManager.Renderer != null)
            {
                foreach (var component in _contents)
                {
                    component.LoadContent();
                }
            }
        }

        private static void AddToGrid(Entity Entity)
        {
            if (!_gridContents.ContainsKey(Entity.GetLocation()))
            {
                _gridContents.Add(Entity.GetLocation(), new List<Entity>() { Entity });
            }
            else
            {
                _gridContents[Entity.GetLocation()].Add(Entity);
            }
        }

        public static void UpdateGridLocation(Entity Entity, Point2 oldLocation)
        {
            if(_gridContents!=null && oldLocation !=null)
            {
                _gridContents[oldLocation].Remove(Entity);
                AddToGrid(Entity);
            }
        }

        private static List<IActor> _players = new List<IActor>();
        public static IEnumerable<IActor> GetPlayers()
        {
            _players.Clear();
            foreach (var tile in _contents)
            {
                if (tile.GetEntityType() == EntityType.ACTOR && (tile as IActor).GetActorType() == ActorType.PLAYER)
                {
                    _players.Add((tile as IActor));
                }
            }
            return _players;
        }

        //Returns a space that has no creatures on it
        private static IEnumerable<Point2> emptyLocations;
        public static Point2 GetEmptyLocation()
        {
            emptyLocations = _gridContents.Where(gc =>
                !gc.Value.Any(e => e.GetEntityType() == EntityType.ACTOR) &&
                gc.Key.GridX > 0 && gc.Key.GridY > 0 &&
                gc.Key.GridX < GameManager.TileMapWidth - 1 && gc.Key.GridY < GameManager.TileMapHeight - 1).
                Select(gc => gc.Key);
            return emptyLocations.ElementAt(RNG.Rand.Next(0, emptyLocations.Count()));
        }

        //Deprecated methods
        public static ICollection<Entity> GetEntitiesToCache()
        {
            return _contents.Where(c => c.GetEntityType() != EntityType.FLOOR).ToList();
        }
        public static IActor GetTouchingCreature(Entity entity)
        {
            return _contents.Where(c=>c.GetEntityType() == EntityType.ACTOR).FirstOrDefault(c => c.Contains(entity.GetLocation())) as IActor;
        }
    }
}