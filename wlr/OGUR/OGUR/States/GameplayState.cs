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
    public class GameplayState : State
    {
        public GameplayState()
        {
            GameplayObjectManager.Reset();
        }
        public override void Update()
        {
            GameplayObjectManager.Update();
        }
        public override void LoadContent()
        {
            GameplayObjectManager.LoadContent();
        }
        public override void Draw()
        {
            GameplayObjectManager.Draw();
        }
    }
}