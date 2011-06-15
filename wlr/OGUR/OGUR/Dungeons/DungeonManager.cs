using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Management;

namespace OGUR.Dungeons
{
    class DungeonManager
    {
        public static int BlocksHigh = 20;
        public static int BlocksWide = 30;
        static Dictionary<int, Dungeon> s_dungeons = new Dictionary<int, Dungeon>();
        static int s_currentDungeon = 0;
        public static void Start()
        {
            s_dungeons.Add(s_currentDungeon,new Dungeon());
        }
        public static void GotoNext()
        {
            s_currentDungeon++;
            LoadOrCreateDungeon();
        }
        public static void GotoPrevious()
        {
            if (s_currentDungeon > 0)
            {
                s_currentDungeon--;
                LoadOrCreateDungeon();
            }
        }
        private static void LoadOrCreateDungeon()
        {
            try
            {
                s_dungeons[s_currentDungeon].LoadTiles();  
            }
            catch(Exception e)
            {
                s_dungeons.Add(s_currentDungeon, new Dungeon());
                s_dungeons[s_currentDungeon].LoadTiles();
            }
        }
    }
}
