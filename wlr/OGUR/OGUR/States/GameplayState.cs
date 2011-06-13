using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using OGUR.GameObjects;
using OGUR.Management;
using OGUR.Sprites;
using OGUR.Dungeons;

namespace OGUR.States
{
    class GameplayState:State
    {
        public GameplayState()
        {
            DungeonManager.Start();
        }
    }
}
