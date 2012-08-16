package com.spx.devtools;import java.util.*;import com.spx.text.*;import com.spx.core.*;import com.xna.wrapper.*;
    public class DevConsole
    {
        private class ConsoleText  extends  com.spx.text.Text
        {
            public ConsoleText(int x, int y, String content)
                { super(content, x, y, TextType.Action);
            }

@Override            public  void Draw()
            {                
                XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position, Color.White, 0, Vector2.Zero, 1.0f, SpriteEffects.None, .9995f);
            }

            public String GetContent()
            {
                return _contents;
            }
        }

        private static DevConsole __instance;
        public static DevConsole Get()
        {
            if (__instance == null)
            {
                __instance = new DevConsole();
            }
            return __instance;
        }

        private ConsoleText[] _contents = new ConsoleText[10];
        private int _index = 0;
        private boolean _isVisible = false;
        private Color _bgColor;

        private DevConsole()
        {
            _bgColor = Color.Black;
            _bgColor.A = (byte)180;
            Add("The console has been started.");
        }

        public void Add(String message)
        {
            if (_index < _contents.length)
            {
                _contents[_index++] = new ConsoleText(50, _index * 50 + 50, message);
            }
            else
            {
                for (int ii = 0; ii < _contents.length-1; ii++)
                {
                    _contents[ii] = new ConsoleText(50,ii * 50 + 50,_contents[ii+1].GetContent());
                }
                _contents[_contents.length - 1] = new ConsoleText(50, (_contents.length-1) * 50 + 50, message);
            }
        }

        public void Draw()
        {
            if (_isVisible)
            {
                XnaManager.Renderer.Draw(XnaManager.GetMenuBaseAsset(), Vector2.Zero, new Rectangle(0, 0, XnaManager.WindowWidth, XnaManager.WindowHeight), _bgColor, 0f, Vector2.Zero, 1, SpriteEffects.None, .9993f);
                for (int ii = 0; ii < _contents.length; ii++)
                {
                    if (_contents[ii] != null)
                    {
                        _contents[ii].Draw();
                    }
                }
            }
        }

        public void Toggle()
        {
            _isVisible = !_isVisible;
        }
    }
