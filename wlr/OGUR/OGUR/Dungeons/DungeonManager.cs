using System;
using System.Collections.Generic;
using OGUR.GameObjects;

namespace OGUR.Dungeons
{
    public class DungeonManager
    {
        public static int BlocksHigh = 20;
        public static int BlocksWide = 30;
        private static Dictionary<int, Dungeon> s_dungeons = new Dictionary<int, Dungeon>();
        private static int s_currentDungeon;
        private static List<GameplayObject> m_cache = new List<GameplayObject>(); 

        public static void Start()
        {
            s_dungeons.Add(s_currentDungeon, new Dungeon());
        }

        public static void GotoNext()
        {
            s_dungeons[s_currentDungeon].CacheContents();
            s_currentDungeon++;
            LoadOrCreateDungeon(false);
        }

        public static void GotoPrevious()
        {
            if (s_currentDungeon > 0)
            {
                s_dungeons[s_currentDungeon].CacheContents();
                s_currentDungeon--;
                LoadOrCreateDungeon(true);
            }
        }

        private static void LoadOrCreateDungeon(bool goingUp)
        {
            if (!s_dungeons.ContainsKey(s_currentDungeon))
            {
                s_dungeons.Add(s_currentDungeon, new Dungeon());
            }
            s_dungeons[s_currentDungeon].LoadTiles(goingUp);
        }

        public static void AddToCache(GameplayObject gameObject)
        {
            m_cache.Add(gameObject);
        }

        public static List<GameplayObject> FlushCache()
        {
            var result = new List<GameplayObject>(m_cache);
            m_cache.Clear();
            return result;
        }
    }
}