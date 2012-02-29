using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;

namespace OGUR.Text
{
    public class Text
    {
        protected string m_contents;
        protected Vector2 m_position = new Vector2(0,0);
        protected int m_textType = TextType.Inventory;

        public Text()
        { }

        public void Reset(string contents, int x, int y)
        {
            m_contents = contents;
            m_position.X = x;
            m_position.Y = y;
        }

        public Text(string contents,int x, int y,int type)
        {
            Reset(contents, x, y);
            m_textType = type;
        }
        public virtual int Update()
        {
            return 0;
        }

        public int GetTextType()
        {
            return m_textType;
        }

        protected virtual void DrawText(SpriteBatch target)
        {
        }

        public virtual void Draw()
        {
           
        }
    }
}
