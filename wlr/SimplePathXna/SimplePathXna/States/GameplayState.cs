using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using welikerogues.GameObjects;
using welikerogues.Management;
using welikerogues.Sprites;
using welikerogues.Dungeons;

namespace welikerogues.States
{
    class GameplayState:State
    {
        public GameplayState()
        {
            DungeonManager.Start();
            List<GameplayObject> items = GameplayObjectManager.GetObjects();
            foreach (GameplayObject item in items)
            {
                AddLocal(item);
            }
        }
    }
}
