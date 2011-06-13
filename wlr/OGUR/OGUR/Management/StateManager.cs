using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using OGUR.States;

namespace OGUR.Management
{
    class StateManager
    {
        static private State m_state;
        static public void LoadState(State state)
        {
            m_state = state;
            GameplayObjectManager.LoadContent();
            GameplayObjectManager.Draw();
        }
        static public void Draw()
        {
            GameplayObjectManager.Draw();
        }
        static public void LoadContent()
        {
            GameplayObjectManager.LoadContent();
        }
        static public void Update()
        {
            GameplayObjectManager.Update();
        }
    }
}
