using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SPX.States
{
    public class StateManager
    {
        private static State _state;

        public static void LoadState(State state)
        {
            _state = state;
            _state.LoadContent();
        }

        public static void Draw()
        {
            _state.Draw();
        }

        public static void LoadContent()
        {
            _state.LoadContent();
        }

        public static void Update()
        {   
            _state.Update();
        }
    }
}