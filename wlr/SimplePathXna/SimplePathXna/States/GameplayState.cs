using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using welikerogues.GameObjects;
using welikerogues.Management;
using welikerogues.Factory;
using welikerogues.Sprites;
using welikerogues.Dungeons;

namespace welikerogues.States
{
    class GameplayState:State
    {
        Dungeon currentLevel = new Dungeon();
        public GameplayState()
        {
            foreach (GameplayObject tile in DungeonManager.GetNext())
            {
                Add(tile);
            }
        }
    }
}
