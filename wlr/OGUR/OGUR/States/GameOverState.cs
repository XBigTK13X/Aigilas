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
using OGUR.Text;

namespace OGUR.States
{
    public class GameOverState : State
    {
        private readonly Texture2D m_menuBase;

        public GameOverState()
        {
            m_menuBase = XnaManager.GetGameOverAsset();
        }

        public override void Draw()
        {
            var x = (XnaManager.WindowWidth - m_menuBase.Bounds.Right)/2;
            var y = (XnaManager.WindowHeight - m_menuBase.Bounds.Bottom) / 2;
            XnaManager.Renderer.Draw(m_menuBase, new Vector2(x,y), Color.White);
        }

        public override void Update()
        {
            if(InputManager.IsPressed(Commands.Confirm,0,true))
            {
                StateManager.LoadState(new GameplayState());
            }
        }

        public override void LoadContent()
        {

        }
    }
}