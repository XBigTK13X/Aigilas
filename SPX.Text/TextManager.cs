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
        static private List<Text> _contents = new List<Text>();

        public static void Add(Text textToAdd)
        {
            if(!_contents.Contains(textToAdd))
            {
                _contents.Add(textToAdd);    
            }
        }

        public static void Clear()
        {
            _contents.Clear();
        }

        static public SpriteFont GetFont()
        {
            return actionFont;
        }

        public static void Update()
        {
            for (int ii = 0; ii < _contents.Count; ii++)
            {
                if(_contents[ii].Update()<=0)
                {
                    _contents.Remove(_contents[ii]);
                    ii--;
                }
            }
        }

        public static void Draw()
        {
            foreach (Text component in _contents)
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
