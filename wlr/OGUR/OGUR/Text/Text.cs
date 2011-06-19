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
        protected string m_contents = "";
        protected Vector2 m_position;

        public Text(string contents,int x, int y)
        {
            m_contents = contents;
            m_position = new Vector2(x,y);
        }
        public virtual int Update()
        {
            return 0;
        }

        protected virtual void DrawText(SpriteBatch target)
        {
        }

        public virtual void Draw()
        {
           
        }
    }
}
