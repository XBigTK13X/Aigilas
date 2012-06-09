using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using SPX.Core;

namespace SPX.Text
{
    public class Text
    {
        protected string _contents;
        protected Vector2 _position = new Vector2(0,0);
        protected int _textType = TextType.Inventory;

        public Text()
        { }

        public void Reset(string contents, int x, int y)
        {
            _contents = contents;
            _position.X = x;
            _position.Y = y;
        }

        public Text(string contents,int x, int y,int type)
        {
            Reset(contents, x, y);
            _textType = type;
        }
        public virtual int Update()
        {
            return 0;
        }

        public int GetTextType()
        {
            return _textType;
        }

        protected virtual void DrawText(SpriteBatch target)
        {
        }

        public virtual void Draw()
        {
           
        }
    }
}
