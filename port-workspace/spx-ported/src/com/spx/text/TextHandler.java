package com.spx.text;import com.spx.wrapper.*;import java.util.*;import com.spx.core.*;
    public class TextHandler
    {
        private DefaultHudText[] defaultPool = new DefaultHudText[100];
        private int defaultIndex = 0;

        private List<Text> _contents = new ArrayList<Text>();

        public TextHandler()
        {
            for (int ii = 0; ii < defaultPool.length; ii++)
            {
                defaultPool[ii] = new DefaultHudText(.2f);
            }
        }

        public void WriteDefault(String contents,int x, int y,Vector2 origin)
        {
            defaultPool[defaultIndex].Reset(contents,x,y,origin);
            Add(defaultPool[defaultIndex]);
            defaultIndex = (defaultIndex + 1) % defaultPool.length;
        }

        public void Add(Text textToAdd)
        {
            if(!_contents.contains(textToAdd))
            {
                _contents.add(textToAdd);    
            }
        }

        public void Clear()
        {
            _contents.clear();
        }

        public void Update()
        {
            for (int ii = 0; ii < _contents.size(); ii++)
            {
                if(_contents.get(ii).Update()<=0)
                {
                    _contents.remove(_contents.get(ii));
                    ii--;
                }
            }
        }

        public void Draw()
        {

            if (XnaManager.Renderer != null)
            {
                for(Text component: _contents)
                {
                    component.Draw();
                }
            }
        }
    }
