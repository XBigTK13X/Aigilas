using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework.Graphics;
using SPX.Core;
using SPX.Text;
using Microsoft.Xna.Framework;
using System;

namespace SPX.Text
{
    [Serializable]
    public class ActionTextHandler
    {
        private ActionText[] defaultPool = new ActionText[100];
        private int defaultIndex = 0;

        private List<Text> _contents = new List<Text>();

        public ActionTextHandler()
        {
            for (int ii = 0; ii < defaultPool.Count(); ii++)
            {
                defaultPool[ii] = new ActionText();
            }
        }

        public void WriteAction(string contents, int lifespan, int x, int y)
        {
            defaultPool[defaultIndex].Reset(contents,lifespan,x, y);
            Add(defaultPool[defaultIndex]);
            defaultIndex = (defaultIndex + 1) % defaultPool.Count();
        }

        public void Add(Text textToAdd)
        {
            if (!_contents.Contains(textToAdd))
            {
                _contents.Add(textToAdd);
                TextManager.Add(textToAdd);
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
                if (_contents[ii].Update() <= 0)
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
