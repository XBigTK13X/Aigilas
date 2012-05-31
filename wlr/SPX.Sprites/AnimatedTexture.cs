using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework;
using SPX.Core;
using System.Runtime.Serialization;

namespace SPX.Sprites
{
    [Serializable()]
    public class AnimatedTexture:ISerializable
    {
        private int _currentFrame;
        private SpriteInfo _spriteInfo;
        private Rectangle _currentCell;
        private Rectangle _target;
        private int _animationTimer;
        private Color _color = Color.White;
        private readonly Texture2D _texture = XnaManager.GetSpriteAsset();
        private float _layerDepth = 0f;
        protected Vector2 _position = Vector2.Zero;

        public AnimatedTexture() { }

        public AnimatedTexture(SerializationInfo info, StreamingContext context)
        {
            _spriteInfo = (SpriteInfo)info.GetValue("AnimatedTexture.SpriteInfo", typeof(SpriteInfo));
            _position = (Vector2)info.GetValue("AnimatedTexture.Position", typeof(Vector2));
            _color = (Color)info.GetValue("AnimatedTexture.Color", typeof(Color));
            _layerDepth = (float)info.GetValue("AnimatedTexture.Depth", typeof(float));
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue("AnimatedTexture.SpriteInfo", _spriteInfo);
            info.AddValue("AnimatedTexture.Position", _position);
            info.AddValue("AnimatedTexture.Color", _color);
            info.AddValue("AnimatedTexture.Depth", _layerDepth);
        }

        public void LoadContent(int assetName)
        {
            _spriteInfo = SpriteSheetManager.GetSpriteInfo(assetName);
            _currentFrame = 0;
            _animationTimer = GameManager.AnimationFps;
        }

        public void Draw()
        {
            UpdateAnimation();
            _currentCell = new Rectangle((_currentFrame * _spriteInfo.X) + (_currentFrame+1), (_spriteInfo.SpriteIndex * _spriteInfo.Y) + (_spriteInfo.SpriteIndex+1),
                                          _spriteInfo.X, _spriteInfo.Y);

            _target = new Rectangle((int)_position.X, (int)_position.Y, GameManager.SpriteWidth, GameManager.SpriteHeight);
            XnaManager.Renderer.Draw(_texture, _target,_currentCell,_color,0f,Vector2.Zero,SpriteEffects.None,_layerDepth);
            
        }

        private void UpdateAnimation()
        {
            if (_spriteInfo.MaxFrame != 1)
            {
                _animationTimer--;
                if (_animationTimer <= 0)
                {
                    _currentFrame = (_currentFrame + 1)%_spriteInfo.MaxFrame;
                    _animationTimer = GameManager.AnimationFps;
                }
            }
        }

        public void SetSpriteInfo(SpriteInfo sprite)
        {
            if (_spriteInfo != sprite)
            {
                _spriteInfo = sprite;
                _currentFrame = 0;
            }
        }

        public void SetPosition(float x, float y)
        {
            _position.X = (int)x;
            _position.Y = (int)y;
        }

        public void SetPosition(Point2 position)
        {
            _position = new Vector2(position.PosX,position.PosY);
        }

        public void SetColor(Color color)
        {
            _color = color;
        }

        public void SetAlpha(float alpha)
        {
            _color = new Color(_color.R, _color.G, _color.B,alpha);
        }

        public void SetDepth(float depth)
        {
            _layerDepth = depth;
        }        
    }
}