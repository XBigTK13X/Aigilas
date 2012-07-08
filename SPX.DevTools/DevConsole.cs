using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace SPX.Core
{
    public class DevConsole
    {
        private class ConsoleText : SPX.Text.Text
        {
            public ConsoleText(int x, int y, string content)
                : base(content, x, y, TextType.Action)
            {

            }

            public override void Draw()
            {                
                XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position, Color.Black, 0, Vector2.Zero, 1.15f, SpriteEffects.None,.9994f);
                XnaManager.Renderer.DrawString(TextManager.GetFont(), _contents, _position, Color.White, 0, Vector2.Zero, 1.0f, SpriteEffects.None, .9995f);
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
        private bool _isVisible;
        private Color _bgColor;

        private DevConsole()
        {
            _bgColor = Color.Black;
            _bgColor.A = (byte)180;
            Add("The console has been started.");
        }

        public void Add(string message)
        {
            _contents[++_index] = new ConsoleText(50,_index*50,message);
        }

        public void Draw()
        {
            if (_isVisible)
            {
                XnaManager.Renderer.Draw(XnaManager.GetMenuBaseAsset(), Vector2.Zero, new Rectangle(0, 0, XnaManager.WindowWidth, XnaManager.WindowHeight), _bgColor, 0f, Vector2.Zero, 1, SpriteEffects.None, .9993f);
                for (int ii = 0; ii < _contents.Length; ii++)
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
}
