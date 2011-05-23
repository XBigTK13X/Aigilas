using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.GameObjects;
using welikerogues.Management;

namespace welikerogues.Dungeons
{
    class DungeonManager
    {
        static List<Dungeon> s_dungeons = new List<Dungeon>();
        static int s_currentDungeon = 0;
        public static void Start()
        {
            s_dungeons.Add(new Dungeon());
        }
        public static void GotoNext()
        {
            s_currentDungeon++;
            LoadOrCreateDungeon();
        }
        public static void GotoPrevious()
        {
            s_currentDungeon--;
            LoadOrCreateDungeon();
        }
        private static void LoadOrCreateDungeon()
        {            
            if(s_dungeons[s_currentDungeon]==null)
            {
                s_dungeons.Add(new Dungeon());
            }
            else
            {
               s_dungeons[s_currentDungeon].LoadTiles();
            }
        }
    }
}
