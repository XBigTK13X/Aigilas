using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Management;
using OGUR.Text;

namespace OGUR.Text
{
    public class TextHandler
    {
        private List<Text> m_contents = new List<Text>();

        public TextHandler()
        {
        }

        public void Add(Text textToAdd)
        {
            if(!m_contents.Contains(textToAdd))
            {
                m_contents.Add(textToAdd);    
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
                if(m_contents[ii].Update()<=0)
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
