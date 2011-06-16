using System.Collections.Generic;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;

namespace OGUR.Text
{
    class TextManager
    {
        static private SpriteFont actionFont;
        static private List<Text> m_contents = new List<Text>();

        public static void AddMessage(string message,int x, int y)
        {
            m_contents.Add(new Text(message, 30, x, y));
        }

        public static void Clear()
        {
            m_contents.Clear();
        }

        static public SpriteFont GetActionFont()
        {
            return actionFont;
        }

        public static void Update()
        {
            for (int ii = 0; ii < m_contents.Count; ii++)
            {
                if(m_contents[ii].Update()<=0)
                {
                    m_contents.Remove(m_contents[ii]);
                    ii--;
                }
            }
        }

        public static void Draw()
        {
            foreach (Text component in m_contents)
            {
                if (XnaManager.GetRenderTarget() != null)
                {
                    component.Draw();
                }
            }
        }

        public static void LoadContent()
        {
            actionFont = XnaManager.GetContentManager().Load<SpriteFont>("Action");
        }
    }
}
