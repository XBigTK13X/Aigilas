using System;
using System.Collections.Generic;
using System.Linq;
using OGUR.Collision;
using OGUR.Dungeons;
using OGUR.Gods;
using OGUR.Management;
using OGUR.Creatures;
using OGUR.States;

namespace OGUR.GameObjects
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
                return m_contents.FirstOrDefault(item => item.GetObjectType() == type);
            }
            return null;
        }

        public static List<GameplayObject> GetObjects(GameObjectType type,Point2 target)
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
            if(type!=CreatureType.NONPLAYER)
            {
                return m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().Where(item => item.GetCreatureType() == type).ToList();    
            }
            return m_contents.Where(o => o.GetObjectType() == GameObjectType.CREATURE).Cast<ICreature>().Where(item => item.GetCreatureType() != CreatureType.PLAYER).ToList();
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

        public static void Reset()
        {
            m_contents = new List<GameplayObject>();
            CreatureFactory.ResetPlayerCount();
            DungeonFactory.Start();
            God.Reset();
            GameplayObjectManager.LoadContent();
            GameplayObjectManager.Draw();
        }

        public static void Update()
        {
            if(!GetObjects(CreatureType.PLAYER).Any(o=>o.IsActive()))
            {
                Reset();
                StateManager.LoadState(new GameOverState());
            }
            for (int ii = 0; ii < m_contents.Count; ii++)
            {
                if(ii>=m_contents.Count)
                {
                    return;
                }
                if (!m_contents[ii].IsActive())
                {
                    m_contents.Remove(m_contents[ii]);
                    ii--;
                    continue;
                }                
                if (m_contents[ii].GetObjectType() == GameObjectType.CREATURE)
                {
                    var creature = m_contents[ii] as ICreature;
                    if (creature != null) creature.Update();
                }
                else
                {
                    m_contents[ii].Update();
                }
            }
        }

        public static void Draw()
        {
            var players = new List<GameplayObject>();
            if (XnaManager.GetRenderTarget() != null)
            {
                foreach (GameplayObject component in m_contents)
                {

                    if (component.GetObjectType() == GameObjectType.CREATURE)
                    {
                        if (((ICreature) component).GetCreatureType() == CreatureType.PLAYER)
                        {
                            players.Add(component);
                        }
                        else
                        {
                            if (((ICreature) component).IsPlaying())
                            {
                                component.Draw();
                            }
                        }
                    }
                    else
                    {
                        component.Draw();
                    }
                }
            }

            if (XnaManager.GetRenderTarget() != null)
            {
                foreach(var player in players)
                {
                
                    if(((ICreature)player).IsPlaying())
                    {
                        player.Draw();
                    }
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

        public static GameplayObject GetNearestPlayer(GameplayObject target)
        {
            GameplayObject closest = GetObjects(CreatureType.PLAYER).FirstOrDefault();
            foreach (var player in GetObjects(CreatureType.PLAYER))
            {
                if (HitTest.GetDistance(target, player) < HitTest.GetDistance(target, closest))
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
    }
}