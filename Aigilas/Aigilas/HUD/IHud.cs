using System.Collections.Generic;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Aigilas.Creatures;
using SPX.Text;
using SPX.Core;

namespace Aigilas.HUD
{
    public class IHud
    {
        protected bool _isVisible = false;
        protected ICreature _parent;
        protected static Texture2D _menuBase;
        protected readonly TextHandler _textHandler = new TextHandler();
        protected List<Vector2> playerHudPositions = new List<Vector2>() { };
        protected Vector2 _dimensions;

        protected IHud(ICreature owner,int width,int height)
        {
            _parent = owner;
            if (_menuBase == null)
            {
                _menuBase = XnaManager.GetMenuBaseAsset();
            }
            _dimensions = new Vector2(width, height);
            playerHudPositions.Add(new Vector2(0, 0));
            playerHudPositions.Add(new Vector2(XnaManager.WindowWidth-_dimensions.X, 0));
            playerHudPositions.Add(new Vector2(0,XnaManager.WindowHeight-_dimensions.Y));
            playerHudPositions.Add(new Vector2(XnaManager.WindowWidth-_dimensions.X,XnaManager.WindowHeight-_dimensions.Y));
        }

        public virtual void Toggle()
        {
            _isVisible = !_isVisible;
        }

        public bool IsVisible()
        {
            return _isVisible;
        }

        public void LoadContent()
        {
            _menuBase = XnaManager.GetMenuBaseAsset();
        }

        protected Vector2 GetHudOrigin()
        {
            return playerHudPositions[_parent.GetPlayerIndex()];
        }
    }
}
