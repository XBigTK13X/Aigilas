using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;

namespace OGUR.Dungeons
{
    public enum Location
    {
        Start,
        Depths
    }
    public static class DungeonFactory
    {
        public static int BlocksHigh = 20;
        public static int BlocksWide = 30;

        private static readonly Dictionary<Location, DungeonSet> m_world = new Dictionary<Location, DungeonSet>();
        private static readonly List<GameplayObject> m_cache = new List<GameplayObject>(); 

        public static void GetNextFloor(Location area)
        {
            m_world[area].GotoNext(area);
        }

        public static void GetPreviousFloor(Location area)
        {
            m_world[area].GotoPrevious(area);
        }

        public static void AddToCache(GameplayObject content)
        {
            m_cache.Add(content);
        }

        public static IList<GameplayObject> FlushCache()
        {
            var result = new List<GameplayObject>(m_cache);
            m_cache.Clear();
            return result;
        }

        public static void Start()
        {
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

        public DungeonSet(Location target)
        {
            m_floors.Add(m_currentFloor, new Dungeon(target));
        }

        public void GotoNext(Location area)
        {
            m_floors[m_currentFloor].CacheContents();
            m_currentFloor++;
            LoadOrCreateDungeon(false);
        }

        public void GotoPrevious(Location area)
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