using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using SPX.States;
using SPX.Core;

namespace OGUR.States
{
    public class GameOverState : State
    {
        private readonly Texture2D m_menuBase;

        public GameOverState()
        {
            m_menuBase = XnaManager.GetGameOverAsset();
        }

        public void Draw()
        {
            var x = (XnaManager.WindowWidth - m_menuBase.Bounds.Right)/2;
            var y = (XnaManager.WindowHeight - m_menuBase.Bounds.Bottom) / 2;
            XnaManager.Renderer.Draw(m_menuBase, new Vector2(x,y), Color.White);
        }

        public void Update()
        {
            if(InputManager.IsPressed(Commands.Confirm,0,true))
            {
                StateManager.LoadState(new GameplayState());
            }
        }

        public void LoadContent()
        {

        }
    }
}