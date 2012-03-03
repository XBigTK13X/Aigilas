//Modified from source code found here: http://www.david-amador.com/2009/10/xna-camera-2d-with-zoom-and-rotation/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace SPX.Core
{
    public class Camera
    {
        protected float _zoom; // Camera Zoom
        public Matrix _transform; // Matrix Transform
        public Vector2 _position; // Camera Position
        protected float _rotation; // Camera Rotation

        public Camera()
        {
            _zoom = 1.0f;
            _rotation = 0.0f;
            _position = Vector2.Zero;
        }

        public float Zoom
        {
            get { return _zoom; }
            set
            {
                _zoom = value;
                if (_zoom < 0.1f) _zoom = 0.1f;
            } // Negative zoom will flip image
        }

        public float Rotation
        {
            get { return _rotation; }
            set { _rotation = value; }
        }

        // Auxiliary function to move the camera
        public void Move(Vector2 amount)
        {
            _position += amount;
        }

        // Get set position
        public Vector2 Pos
        {
            get { return _position; }
            set { _position = value; }
        }

        public Matrix GetTransformation(GraphicsDevice graphicsDevice)
        {
            _transform = // Thanks to o KB o for this solution
                Matrix.CreateTranslation(new Vector3(-_position.X, -_position.Y, 0))*
                Matrix.CreateRotationZ(Rotation)*
                Matrix.CreateScale(new Vector3(Zoom, Zoom, 1))*
                Matrix.CreateTranslation(new Vector3(graphicsDevice.Viewport.Width*0.5f,
                                                     graphicsDevice.Viewport.Height*0.5f, 0));
            return _transform;
        }
    }
}