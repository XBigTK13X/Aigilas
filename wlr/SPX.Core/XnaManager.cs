using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework;

namespace SPX.Core
{
    public static class XnaManager
    {
        public static readonly int WindowHeight = GameManager.SpriteHeight*20;
        public static readonly int WindowWidth = GameManager.SpriteWidth*30;
        private static ContentManager __assetHandler;
        private static Camera __camera = new Camera();
        private static GraphicsDeviceManager __graphicsDevice;
        public static SpriteBatch Renderer;

        private const string __menuBaseSprite = "MenuBase";
        private const string __gameplaySheetSprite = "GameplaySheet";
        private const string __gameOverSprite = "GameOver";
        private const string __fontName = "Action";

        public static void SetContentManager(ContentManager assetHandler)
        {
            __assetHandler = assetHandler;
        }

        private static Texture2D GetAsset(string resourceName)
        {
            return __assetHandler.Load<Texture2D>(resourceName);
        }

        public static Texture2D GetMenuBaseAsset()
        {
            return GetAsset(__menuBaseSprite);
        }

        public static Texture2D GetSpriteAsset()
        {
            return GetAsset(__gameplaySheetSprite);
        }

        public static Texture2D GetGameOverAsset()
        {
            return GetAsset(__gameOverSprite);
        }

        private static SpriteFont GetFont(string resourceName)
        {
            return __assetHandler.Load<SpriteFont>(resourceName);
        }

        public static SpriteFont GetActionFont()
        {
            return GetFont(__fontName);
        }

        public static void SetupCamera(GraphicsDeviceManager graphicsDevice)
        {
            __camera.Pos = new Vector2(GameManager.SpriteWidth*15, GameManager.SpriteHeight*10);
            __graphicsDevice = graphicsDevice;
            __camera.Zoom = 1f;
            //__graphicsDevice.IsFullScreen = true;
        }

        public static Camera GetCamera()
        {
            return __camera;
        }

        public static GraphicsDeviceManager GetGraphicsDevice()
        {
            return __graphicsDevice;
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