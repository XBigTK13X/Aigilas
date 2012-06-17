using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;
using SPX.Entities;

namespace SPX.Particles
{
    public class Particle2: PEComponent
    {
        public const int DefaultLife = 100;

        private int Height = RNG.Rand.Next(2, 15);
        private int Width = RNG.Rand.Next(2, 15);

        
        private float _life = DefaultLife;
        private Texture2D _texture = XnaManager.GetParticleAsset();
        private Rectangle _currentCell;
        private Rectangle _target;
        private float _layerDepth = .99999f;
        private Color _color = Color.White;
        private float _alpha = 1;
        
        public float MoveSpeed = 5f;

        public IEntity Entity;
        public Point2 Position = new Point2(0, 0);
        public Point2 Origin = new Point2(0, 0);
        public double Angle;
        public float Radius;

        private ParticleBehavior _behavior;

        public Particle2(Point2 position)
        {
        }

        public void Draw()
        {
            if (IsActive)
            {
                _currentCell = new Rectangle(0, 0, 6, 6);
                _target = new Rectangle((int)Position.X, (int)Position.Y, Width, Width);
                XnaManager.Renderer.Draw(_texture, _target, _currentCell, _color*_alpha, 0f, Vector2.Zero, SpriteEffects.None, _layerDepth);
            }
        }

        public void Reset(ParticleBehavior behavior, Point2 position,Color baseColor)
        {
            Init(behavior, position, null, baseColor);
        }

        public void Reset(ParticleBehavior behavior, IEntity entity,Color baseColor)
        {
            Init(behavior, null, entity, baseColor);
        }

        private void Init(ParticleBehavior behavior, Point2 position, IEntity entity,Color baseColor)
        {
            _behavior = behavior;
            if (position != null)
            {
                Origin.Reset(position.X, position.Y);
                Position.Reset(position.X, position.Y);                
            }
            if (entity != null)
            {
                Entity = entity;
                Origin.Reset(Entity.GetLocation().PosX, Entity.GetLocation().PosY);
                Position.Reset(Entity.GetLocation().PosX, Entity.GetLocation().PosY);               
            }           
            if (baseColor != null)
            {
                _color = Darken(baseColor, RNG.Rand.Next(10, 50) / 100f);
            }
            else
            {
                _color = new Color((byte)RNG.Rand.Next(60, 190), (byte)RNG.Rand.Next(60, 190), (byte)RNG.Rand.Next(60, 190));
            }
            Angle = RNG.Angle();
            IsActive = true;
            MoveSpeed = 15f - (10f * ((Height + Width) / 30f));
        }

        private static Color Darken(Color inColor, float inAmount)
        {
            return new Color(
              (int)MathHelper.Clamp(((float)inColor.R) - 255 * inAmount, 0, 255),
              (int)MathHelper.Clamp(((float)inColor.G) - 255 * inAmount, 0, 255),
              (int)MathHelper.Clamp(((float)inColor.B) - 255 * inAmount, 0, 255),
              255);
        }

        public void Update()
        {
            _life *= .85f;
            _alpha *= .999f;
            if (_life <= .001 && (Entity == null || (Entity != null && !Entity.IsActive())))
            {
                IsActive = false;            
            }
            _behavior.Update(this);
        }
    }
}
