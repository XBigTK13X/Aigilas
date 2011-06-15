using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using OGUR.States;

namespace OGUR.Management
{
    public class StateManager
    {
        private static State m_state;

        public static void LoadState(State state)
        {
            m_state = state;
            GameplayObjectManager.LoadContent();
            GameplayObjectManager.Draw();
        }

        public static void Draw()
        {
            GameplayObjectManager.Draw();
        }

        public static void LoadContent()
        {
            GameplayObjectManager.LoadContent();
        }

        public static void Update()
        {
            GameplayObjectManager.Update();
        }
    }
}