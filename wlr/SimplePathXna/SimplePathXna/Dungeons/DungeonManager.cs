using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using welikerogues.GameObjects;

namespace welikerogues.Dungeons
{
    class DungeonManager
    {
        static List<Dungeon> s_dungeons = new List<Dungeon>();
        static int currentDungeon = 0;
        public static List<GameplayObject> GetNext()
        {
            s_dungeons.Add(new Dungeon());
            return s_dungeons[0].GetContents();
        }

    }
}
