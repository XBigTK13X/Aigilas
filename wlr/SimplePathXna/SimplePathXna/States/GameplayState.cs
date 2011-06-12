using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using WeLikeRogues.GameObjects;
using WeLikeRogues.Management;
using WeLikeRogues.Sprites;
using WeLikeRogues.Dungeons;

namespace WeLikeRogues.States
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
