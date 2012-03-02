using System.Collections.Generic;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Creatures;
using SPX.Text;
using SPX.Core;

namespace OGUR.HUD
{
    public class IHud
    {
        protected bool m_isVisible = false;
        protected ICreature m_parent;
        protected static Texture2D m_menuBase;
        protected readonly TextHandler m_textHandler = new TextHandler();
        protected List<Vector2> playerHudPositions = new List<Vector2>() { };
        protected Vector2 m_dimensions;

        protected IHud(ICreature owner,int width,int height)
        {
            m_parent = owner;
            if (m_menuBase == null)
            {
                m_menuBase = XnaManager.GetMenuBaseAsset();
            }
            m_dimensions = new Vector2(width, height);
            playerHudPositions.Add(new Vector2(0, 0));
            playerHudPositions.Add(new Vector2(XnaManager.WindowWidth-m_dimensions.X, 0));
            playerHudPositions.Add(new Vector2(0,XnaManager.WindowHeight-m_dimensions.Y));
            playerHudPositions.Add(new Vector2(XnaManager.WindowWidth-m_dimensions.X,XnaManager.WindowHeight-m_dimensions.Y));
        }

        public virtual void Toggle()
        {
            m_isVisible = !m_isVisible;
        }

        public bool IsVisible()
        {
            return m_isVisible;
        }

        public void LoadContent()
        {
            m_menuBase = XnaManager.GetMenuBaseAsset();
        }

        protected Vector2 GetHudOrigin()
        {
            return playerHudPositions[m_parent.GetPlayerIndex()];
        }
    }
}
