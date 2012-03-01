using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.States
{
    public class StateManager
    {
        private static State m_state;

        public static void LoadState(State state)
        {
            m_state = state;
            m_state.LoadContent();
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