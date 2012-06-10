using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using SPX.States;
using SPX.Core;
using Aigilas.Management;

namespace Aigilas.States
{
    public class GameOverState : State
    {
        private readonly Texture2D _menuBase;

        public GameOverState()
        {
            _menuBase = XnaManager.GetGameOverAsset();
        }

        public void Draw()
        {
            var x = (XnaManager.WindowWidth - _menuBase.Bounds.Right)/2;
            var y = (XnaManager.WindowHeight - _menuBase.Bounds.Bottom) / 2;
            XnaManager.Renderer.Draw(_menuBase, new Vector2(x,y), Color.White);
        }

        public void Update()
        {
            if(Input.IsPressed(Commands.Confirm,0,true))
            {
                StateManager.LoadState(new GameplayState());
            }
        }

        public void LoadContent()
        {

        }
    }
}