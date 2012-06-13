using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace SPX.Particles
{
    public class Particle2: PEComponent
    {
        public const int DefaultLife = 100;

        private int Height = RNG.Rand.Next(2, 15);
        private int Width = RNG.Rand.Next(2, 15);

        private Point2 _position = new Point2(0, 0);
        private float _life = DefaultLife;
        private Texture2D _texture = XnaManager.GetParticleAsset();
        private Rectangle _currentCell;
        private Rectangle _target;
        private float _layerDepth = 0f;
        private Color _color = Color.White;
        private float _alpha = 1;
        public float MoveSpeed = 5f;
        
        private Point2 _origin = new Point2(0, 0);
        private double angle;

        private ParticleBehavior _behavior;

        public Particle2(Point2 position)
        {
        }

        public void Draw()
        {
            if (IsActive)
            {
                _currentCell = new Rectangle(0, 0, 6, 6);
                _target = new Rectangle((int)_position.X, (int)_position.Y, Width, Width);
                XnaManager.Renderer.Draw(_texture, _target, _currentCell, _color*_alpha, 0f, Vector2.Zero, SpriteEffects.None, _layerDepth);
            }
        }

        public void Reset(ParticleBehavior behavior, Point2 position)
        {
            _behavior = behavior;
            _origin.Reset(position.X, position.Y);
            _position.Reset(position.X, position.Y);
            angle = RNG.Angle();
            IsActive = true;
            _color = new Color((byte)RNG.Rand.Next(60, 190), (byte)RNG.Rand.Next(60, 190), (byte)RNG.Rand.Next(60, 190));
            MoveSpeed = 10 - (10f * ((Height + Width) / 30f));
        }

        public void Update()
        {
            _life *= .85f;
            _alpha *= .999f;
            if (_life <= .001)
            {
                IsActive = false;
            }
            _position.Copy(_behavior.Update(_position, _origin, angle,this));
        }
    }
}
