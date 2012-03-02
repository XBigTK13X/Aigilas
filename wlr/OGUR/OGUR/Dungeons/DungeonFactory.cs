using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;

namespace OGUR.Dungeons
{
    public class Location
    {
        public const int Start = 0;
        public const int Depths = 1;
    }
    public static class DungeonFactory
    {
        public static int BlocksHigh = 20;
        public static int BlocksWide = 30;

        private static Dictionary<int, DungeonSet> m_world = new Dictionary<int, DungeonSet>();
        private static List<Entity> m_cache = new List<Entity>(); 

        public static void GetNextFloor(int area)
        {
            m_world[area].GotoNext(area);
        }

        public static void GetPreviousFloor(int area)
        {
            m_world[area].GotoPrevious(area);
        }

        public static void AddToCache(Entity content)
        {
            m_cache.Add(content);
        }

        public static List<Entity> FlushCache()
        {
            var result = new List<Entity>(m_cache);
            m_cache.Clear();
            return result;
        }

        public static void Start()
        {
            m_world = new Dictionary<int, DungeonSet>();
            m_cache = new List<Entity>();
            m_world.Add(Location.Depths,new DungeonSet(Location.Start));
        }
    }

    internal class DungeonSet
    {
        private int m_currentFloor = 0;
        private readonly Dictionary<int,Dungeon> m_floors = new Dictionary<int, Dungeon>();

        public DungeonSet()
        {
            m_floors.Add(m_currentFloor,new Dungeon());
        }

        public DungeonSet(int target)
        {
            m_floors.Add(m_currentFloor, new Dungeon(target));
        }

        public void GotoNext(int area)
        {
            m_floors[m_currentFloor].CacheContents();
            m_currentFloor++;
            LoadOrCreateDungeon(false);
        }

        public void GotoPrevious(int area)
        {
            if (m_currentFloor > 0)
            {
                m_floors[m_currentFloor].CacheContents();
                m_currentFloor--;
                LoadOrCreateDungeon(true);
            }
        }

        private void LoadOrCreateDungeon(bool goingUp)
        {
            if (!m_floors.ContainsKey(m_currentFloor))
            {
                m_floors.Add(m_currentFloor, new Dungeon());
            }
            m_floors[m_currentFloor].LoadTiles(goingUp);
        }
    }
}