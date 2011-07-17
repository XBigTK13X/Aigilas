using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using OGUR.GameObjects;
using OGUR.States;

namespace OGUR.Management
{
    public class StateManager
    {
        private static State m_state;

        public static void LoadState(State state)
        {
            m_state = state;
            m_state.LoadContent();
            m_state.Draw();
        }

        public static void Draw()
        {
            m_state.Draw();
        }

        public static void LoadContent()
        {
            m_state.LoadContent();
        }

        public static void Update()
        {   
            m_state.Update();
        }
    }
}