using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;

namespace OGUR.Text
{
    class Text
    {
        protected string m_contents;
        protected Vector2 m_position;
        protected TextType m_textType;

        public Text(string contents,int x, int y,TextType type)
        {
            m_textType = type;
            m_contents = contents;
            m_position = new Vector2(x,y);
        }
        public virtual int Update()
        {
            return 0;
        }

        public TextType GetTextType()
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
