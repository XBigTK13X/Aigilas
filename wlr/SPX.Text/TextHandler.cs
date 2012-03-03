using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework.Graphics;
using SPX.Core;
using Microsoft.Xna.Framework;

namespace SPX.Text
{
    public class TextHandler
    {
        private DefaultHudText[] defaultPool = new DefaultHudText[100];
        private int defaultIndex = 0;

        private List<Text> _contents = new List<Text>();

        public TextHandler()
        {
            for (int ii = 0; ii < defaultPool.Count(); ii++)
            {
                defaultPool[ii] = new DefaultHudText(.2f);
            }
        }

        public void WriteDefault(string contents,int x, int y,Vector2 origin)
        {
            defaultPool[defaultIndex].Reset(contents,x,y,origin);
            Add(defaultPool[defaultIndex]);
            defaultIndex = (defaultIndex + 1) % defaultPool.Count();
        }

        public void Add(Text textToAdd)
        {
            if(!_contents.Contains(textToAdd))
            {
                _contents.Add(textToAdd);    
            }
        }

        public void Clear()
        {
            _contents.Clear();
        }

        public void Update()
        {
            for (var ii = 0; ii < _contents.Count; ii++)
            {
                if(_contents[ii].Update()<=0)
                {
                    _contents.Remove(_contents[ii]);
                    ii--;
                }
            }
        }

        public void Draw()
        {

            if (XnaManager.Renderer != null)
            {
                foreach (var component in _contents)
                {
                    component.Draw();
                }
            }
        }
    }
}
