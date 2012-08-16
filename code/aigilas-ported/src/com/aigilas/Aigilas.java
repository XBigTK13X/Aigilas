package com.aigilas;import com.xna.wrapper.*;import java.util.*;import com.aigilas.states.*;import com.spx.states.*;import com.spx.core.*;import com.spx.text.*;import com.aigilas.management.*;import com.spx.sprites.*;import com.spx.particles.*;import com.spx.io.*;import com.spx.devtools.*;
    public class Aigilas extends  com.xna.wrapper.Game
    {
        private GraphicsDeviceManager graphics;

        public Aigilas()
        {
            this.TargetElapsedTime = TimeSpan.FromSeconds(1.0f / 60.0f);
            Client.Get();
            graphics = new GraphicsDeviceManager(this);
            XnaManager.SetupCamera(graphics,false);
            Content.RootDirectory = "Content";
        }

@Overridepublic  void Initialize()
        {
            XnaManager.SetContentManager(this.Content);
            Input.Setup(new InputInitializer());
            SpriteSheetManager.Setup(new SpriteInitializer());
            StateManager.LoadState(new MainMenuState());
            ParticleEngine.Reset();
            super.Initialize();
        }

@Override        protected  void LoadContent()
        {
            XnaManager.Renderer = new SpriteBatch(GraphicsDevice);
            StateManager.LoadContent();
            TextManager.LoadContent();
            //$$$MediaPlayer.Play(Content.Load<Song>("MainTheme"));
            MediaPlayer.IsRepeating = true;
        }

@Override        protected  void UnloadContent()
        {
            
        }

@Override        protected  void Update(GameTime gameTime)
        {
            Input.Update();
            if (Input.DetectState(Commands.ToggleDevConsole, Client.Get().GetFirstPlayerIndex()))
            {
                DevConsole.Get().Toggle();
            }
            if (Client.Get().NextTurn())
            {
                for (int ii = 0; ii < 4; ii++)
                {
                    PlayerIndex player = PlayerIndex.values()[ii];
                    if (GamePad.GetState(player).IsPressed(Buttons.Back) && GamePad.GetState(player).IsPressed(Buttons.Start))
                    {
                        SetIsRunning(false);
                    }
                }
                ParticleEngine.Update();
                StateManager.Update();
                TextManager.Update();
                super.Update(gameTime);
                Client.Get().PrepareForNextTurn();
            }
            else
            {
                Client.Get().HeartBeat();
            }
        }

@Override        protected  void Draw(GameTime gameTime)
        {
            GraphicsDevice.clear(Color.White);
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
            super.Draw(gameTime);
            XnaManager.Renderer.End();
        }
    }
