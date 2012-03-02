using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework.Graphics;
using SPX.Core;

namespace SPX.Text
{
    public class TextType
    {
        public const int Action = 0;
        public const int Inventory = 1;
    }

    public class TextManager
    {
        static private SpriteFont actionFont;
        static private List<Text> m_contents = new List<Text>();

        public static void Add(Text textToAdd)
        {
            if(!m_contents.Contains(textToAdd))
            {
                m_contents.Add(textToAdd);    
            }
        }

        public static void Clear()
        {
            m_contents.Clear();
        }

        static public SpriteFont GetFont()
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
                if (XnaManager.Renderer != null)
                {
                    component.Draw();
                }
            }
        }

        public static void LoadContent()
        {
            actionFont = XnaManager.GetActionFont();
        }
    }
}
