using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework;
using OGUR.Sprites;

namespace OGUR.Management
{
    public static class XnaManager
    {
        public static readonly int WindowHeight = SpriteInfo.Height*20;
        public static readonly int WindowWidth = SpriteInfo.Width*30;
        private static ContentManager s_assetHandler;
        private static Camera s_camera = new Camera();
        private static GraphicsDeviceManager s_graphicsDevice;
        public static SpriteBatch Renderer;

        public static void SetContentManager(ContentManager assetHandler)
        {
            s_assetHandler = assetHandler;
        }

        public static ContentManager GetContentManager()
        {
            return s_assetHandler;
        }

        public static void SetupCamera(GraphicsDeviceManager graphicsDevice)
        {
            s_camera.Pos = new Vector2(SpriteInfo.Width*15, SpriteInfo.Height*10);
            s_camera.Zoom = 1f;
            s_graphicsDevice = graphicsDevice;
        }

        public static Camera GetCamera()
        {
            return s_camera;
        }

        public static GraphicsDeviceManager GetGraphicsDevice()
        {
            return s_graphicsDevice;
        }

        public static Vector2 GetCenter()
        {
            return new Vector2(WindowWidth/2, WindowHeight/2);
        }

        public static Vector2 GetDimensions()
        {
            return new Vector2(WindowWidth,WindowHeight);
        }
    }
}