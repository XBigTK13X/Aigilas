using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Entities;
using SPX.Entities;
using SPX.Saves;
using System.Runtime.Serialization;

namespace OGUR.Dungeons
{
    public class Location
    {
        public const int Start = 0;
        public const int Depths = 1;
    }
    [Serializable]
    public static class DungeonFactory
    {
        public static int BlocksHigh = 20;
        public static int BlocksWide = 30;

        private static int __floorCount = 0;

        private static Dictionary<int, DungeonSet> _world = new Dictionary<int, DungeonSet>();
        private static List<Entity> _cache = new List<Entity>();

        private static void SaveGame()
        {
            Save<OgurSave>.Init(new OgurSave(_world, _cache,__floorCount));
        }

        public static void GetNextFloor(int area)
        {
            _world[area].GotoNext(area);
            SaveGame();
        }

        public static void GetPreviousFloor(int area)
        {
            _world[area].GotoPrevious(area);
            SaveGame();
        }

        public static void AddToCache(Entity content)
        {
            _cache.Add(content);
        }

        public static List<Entity> FlushCache()
        {
            var result = new List<Entity>(_cache);
            _cache.Clear();
            return result;
        }

        public static void Start()
        {
            _world = new Dictionary<int, DungeonSet>();
            _cache = new List<Entity>();
            _world.Add(Location.Depths,new DungeonSet(Location.Start));
        }

        public static void Start(OgurSave saveData)
        {
            _world = saveData.World;
            _cache = saveData.Cache;
            __floorCount = saveData.FloorCount;
        }

        public static int GetFloorCount()
        {
            return __floorCount;
        }

        public static void IncreaseFloorCount()
        {
            __floorCount++;
        }
    }

    [Serializable]
    public class DungeonSet
    {
        private int _currentFloor = 0;
        private Dictionary<int,Dungeon> _floors = new Dictionary<int, Dungeon>();

        public DungeonSet()
        {
            _floors.Add(_currentFloor,new Dungeon());
        }

        public DungeonSet(int target)
        {
            _floors.Add(_currentFloor, new Dungeon(target));
        }

        public void GotoNext(int area)
        {
            _floors[_currentFloor].CacheContents();
            _currentFloor++;
            LoadOrCreateDungeon(false);
        }

        public void GotoPrevious(int area)
        {
            if (_currentFloor > 0)
            {
                _floors[_currentFloor].CacheContents();
                _currentFloor--;
                LoadOrCreateDungeon(true);
            }
        }

        private void LoadOrCreateDungeon(bool goingUp)
        {
            if (!_floors.ContainsKey(_currentFloor))
            {
                _floors.Add(_currentFloor, new Dungeon());
                DungeonFactory.IncreaseFloorCount();
            }
            _floors[_currentFloor].LoadTiles(goingUp);
        }
    }
}