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
            m_menuBase = XnaManager.GetContentManager().Load<Texture2D>("GameOver");
        }

        public override void Draw()
        {
            SpriteBatch target = XnaManager.GetRenderTarget();
            target.Begin(SpriteSortMode.BackToFront,
                         BlendState.AlphaBlend,
                         null,
                         null,
                         null,
                         null,
                         XnaManager.GetCamera().GetTransformation(XnaManager.GetGraphicsDevice().GraphicsDevice));
            var x = (XnaManager.WindowWidth - m_menuBase.Bounds.Right)/2;
            var y = (XnaManager.WindowHeight - m_menuBase.Bounds.Bottom) / 2;
            target.Draw(m_menuBase, new Vector2(x,y), Color.White);
            target.End();
        }

        public override void Update()
        {

        }

        public override void LoadContent()
        {

        }
    }
}