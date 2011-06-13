//Modified from source code found here: http://www.david-amador.com/2009/10/xna-camera-2d-with-zoom-and-rotation/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace OGUR.Management
{
    class Camera
    {
        protected float m_zoom; // Camera Zoom
        public Matrix m_transform; // Matrix Transform
        public Vector2 m_position; // Camera Position
        protected float m_rotation; // Camera Rotation

        public Camera()
        {
            m_zoom = 1.0f;
            m_rotation = 0.0f;
            m_position = Vector2.Zero;
        }
        public float Zoom
        {
            get { return m_zoom; }
            set { m_zoom = value; if (m_zoom < 0.1f) m_zoom = 0.1f; } // Negative zoom will flip image
        }

        public float Rotation
        {
            get { return m_rotation; }
            set { m_rotation = value; }
        }

        // Auxiliary function to move the camera
        public void Move(Vector2 amount)
        {
            m_position += amount;
        }
        // Get set position
        public Vector2 Pos
        {
            get { return m_position; }
            set { m_position = value; }
        }
        public Matrix GetTransformation(GraphicsDevice graphicsDevice)
        {
            m_transform =       // Thanks to o KB o for this solution
              Matrix.CreateTranslation(new Vector3(-m_position.X, -m_position.Y, 0)) *
                                         Matrix.CreateRotationZ(Rotation) *
                                         Matrix.CreateScale(new Vector3(Zoom, Zoom, 1)) *
                                         Matrix.CreateTranslation(new Vector3(graphicsDevice.Viewport.Width * 0.5f, graphicsDevice.Viewport.Height * 0.5f, 0));
            return m_transform;
        }
    }
}
