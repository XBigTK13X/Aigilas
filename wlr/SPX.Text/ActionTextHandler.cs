using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;
using OGUR.Text;
using Microsoft.Xna.Framework;

namespace OGUR.Text
{
    public class ActionTextHandler
    {
        private ActionText[] defaultPool = new ActionText[100];
        private int defaultIndex = 0;

        private List<Text> m_contents = new List<Text>();

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
            if (!m_contents.Contains(textToAdd))
            {
                m_contents.Add(textToAdd);
                TextManager.Add(textToAdd);
            }
        }

        public void Clear()
        {
            m_contents.Clear();
        }

        public void Update()
        {
            for (var ii = 0; ii < m_contents.Count; ii++)
            {
                if (m_contents[ii].Update() <= 0)
                {
                    m_contents.Remove(m_contents[ii]);
                    ii--;
                }
            }
        }

        public void Draw()
        {

            if (XnaManager.Renderer != null)
            {
                foreach (var component in m_contents)
                {
                    component.Draw();
                }
            }
        }
    }
}
