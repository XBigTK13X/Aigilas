using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Aigilas.States;
using SPX.States;
using SPX.Core;
using SPX.Text;
using Aigilas.Management;
using SPX.Sprites;
using Microsoft.Xna.Framework.Media;
using SPX.Particles;
using SPX.IO;
using Aigilas.IO;
using System.Threading;
using SPX.DevTools;

namespace Aigilas
{
    public class Game : Microsoft.Xna.Framework.Game
    {
        private GraphicsDeviceManager graphics;

        public Game()
        {
            Server.Get().Update();
            Thread.Sleep(100);
            Client.Get().Update();
            Thread.Sleep(100);
            graphics = new GraphicsDeviceManager(this);
            XnaManager.SetupCamera(ref graphics,false);
            Content.RootDirectory = "Content";
        }

        protected override void Initialize()
        {
            XnaManager.SetContentManager(this.Content);
            Input.Setup(new InputInitializer());
            SpriteSheetManager.Setup(new SpriteInitializer());
            StateManager.LoadState(new MainMenuState());
            ParticleEngine.Reset();
            base.Initialize();
        }

        protected override void LoadContent()
        {
            XnaManager.Renderer = new SpriteBatch(GraphicsDevice); ;
            StateManager.LoadContent();
            TextManager.LoadContent();
            //$$$MediaPlayer.Play(Content.Load<Song>("MainTheme"));
            MediaPlayer.IsRepeating = true;
        }

        protected override void UnloadContent()
        {
            
        }

        protected override void Update(GameTime gameTime)
        {
            Server.Get().Update();
            Client.Get().Update();
            for (int ii = 0; ii < 4;ii++)
            {
                var player = (PlayerIndex) ii;
                if (GamePad.GetState(player).Buttons.Back == ButtonState.Pressed && GamePad.GetState(player).Buttons.Start==ButtonState.Pressed)
                {
                    Exit();
                    return;
                }
            }
            if(Input.IsActive(Commands.ToggleDevConsole,Client.Get().GetFirstPlayerIndex()))
            {
                DevConsole.Get().Toggle();
            }

            Input.Update();
            ParticleEngine.Update();
            StateManager.Update();
            TextManager.Update();
            base.Update(gameTime);
        }

        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.White);
            Resolution.BeginDraw();
            XnaManager.Renderer.Begin(SpriteSortMode.FrontToBack,
                         BlendState.AlphaBlend,
                         null,
                         null,
                         null,
                         null,
                         XnaManager.GetScalingMatrix());
            StateManager.Draw();
            ParticleEngine.Draw();
            TextManager.Draw();
            DevConsole.Get().Draw();
            base.Draw(gameTime);
            XnaManager.Renderer.End();
        }
    }
}