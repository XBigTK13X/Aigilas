using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using OGUR.GameObjects;
using OGUR.Management;


namespace OGUR.States
{
    class State
    {
        protected List<GameplayObject> m_windowComponents = new List<GameplayObject>();
        protected State()
        {
            GameplayObjectManager.Clear();
        }
        public virtual void LoadContent()
        {
            foreach (GameplayObject component in m_windowComponents)
            {
                component.LoadContent();
            }
        }
        
        public virtual void Draw()
        {
            foreach (GameplayObject component in m_windowComponents)
            {
                component.Draw();
            }
        }
        public virtual void Update()
        {
            for(int ii = 0;ii<m_windowComponents.Count;ii++)
            {
                m_windowComponents[ii].Update();
                if (!m_windowComponents[ii].IsActive())
                {
                    m_windowComponents.Remove(m_windowComponents[ii]);
                    ii--;
                }
            }
        }
        protected void Add(GameplayObject gameComponent)
        {
            m_windowComponents.Add(gameComponent);
            GameplayObjectManager.AddObject(gameComponent);
        }
        protected void AddLocal(GameplayObject gameComponent)
        {
            m_windowComponents.Add(gameComponent);
        }
    }
}
