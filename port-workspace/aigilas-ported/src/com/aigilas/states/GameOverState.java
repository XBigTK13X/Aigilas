package com.aigilas.states;import com.xna.wrapper.*;import java.util.*;import com.spx.states.*;import com.spx.core.*;import com.aigilas.management.*;import com.spx.io.*;
    public class GameOverState implements State
    {
        private Texture2D _menuBase;

        public GameOverState()
        {
            _menuBase = XnaManager.GetGameOverAsset();
        }

        public void Draw()
        {
            float x = (XnaManager.WindowWidth - _menuBase.Bounds.Right)/2;
            float y = (XnaManager.WindowHeight - _menuBase.Bounds.Bottom) / 2;
            XnaManager.Renderer.Draw(_menuBase, new Vector2(x,y), Color.White);
        }

        public void Update()
        {
            if(Input.IsActive(Commands.Confirm,0,true))
            {
                StateManager.LoadState(new GameplayState());
            }
        }

        public void LoadContent()
        {

        }
    }
