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
    static class XnaManager
    {
        static public readonly int WindowHeight = SpriteInfo.Height*20;
        static public readonly int WindowWidth = SpriteInfo.Width*30;
        static private ContentManager s_assetHandler;
        static private SpriteBatch s_renderTarget;
        static private Camera s_camera = new Camera();
        static private GraphicsDeviceManager s_graphicsDevice;

        static public void SetContentManager(ContentManager assetHandler)
        {
            s_assetHandler = assetHandler;
        }
        static public void SetRenderTarget(SpriteBatch renderTarget)
        {
            s_renderTarget = renderTarget;
        }
        static public ContentManager GetContentManager()
        {
            return s_assetHandler;
        }
        static public SpriteBatch GetRenderTarget()
        {
            return s_renderTarget;
        }
        static public void SetupCamera(GraphicsDeviceManager graphicsDevice)
        {
            s_camera.Pos = new Vector2(SpriteInfo.Width*15,SpriteInfo.Height*10);
            s_camera.Zoom = 1f;
            s_graphicsDevice = graphicsDevice;
        }
        static public Camera GetCamera()
        {
            return s_camera;
        }

        static public GraphicsDeviceManager GetGraphicsDevice()
        {
            return s_graphicsDevice;
        }
    }
}
