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
            if (XnaManager.GetRenderTarget() != null)
            {
                m_state.LoadContent();
                m_state.Draw();
            }
        }
        static public void Draw()
        {
            m_state.Draw();
        }
        static public void LoadContent()
        {
            m_state.LoadContent();
        }
        static public void Update()
        {
            m_state.Update();
        }
    }
}
