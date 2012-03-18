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
        public static readonly int WindowHeight = GameManager.SpriteHeight*20; //720
        public static readonly int WindowWidth = GameManager.SpriteWidth*30; //1280
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

        private static float CalculateCameraZoom()
        {
            if (WindowHeight > WindowWidth)
            {
                return 1 + ((float)(WindowWidth - ((float)GameManager.SpriteWidth * GameManager.TileMapWidth)) / WindowWidth);
            }
            return 1 + (((float)WindowHeight - ((float)GameManager.SpriteHeight * GameManager.TileMapHeight)) / WindowHeight);
        }

        public static void SetupCamera(GraphicsDeviceManager graphicsDevice,bool isFullScreen)
        {
            //Camera is on the gameboard center
            __camera.Pos = new Vector2(GameManager.SpriteWidth * GameManager.TileMapWidth/2, GameManager.SpriteHeight * GameManager.TileMapHeight/2);
            __graphicsDevice = graphicsDevice;
            __graphicsDevice.IsFullScreen = isFullScreen;
            Resolution.Init(ref graphicsDevice);
            Resolution.SetVirtualResolution(WindowWidth, WindowHeight);
            Resolution.SetResolution(WindowWidth, WindowHeight, isFullScreen);
            __camera.Zoom = CalculateCameraZoom();
        }

        public static Vector2 GetCenter()
        {
            return new Vector2(WindowWidth/2, WindowHeight/2);
        }

        public static Vector2 GetDimensions()
        {
            return new Vector2(WindowWidth,WindowHeight);
        }

        public static Matrix GetScalingMatrix()
        {
            return __camera.GetTransformation(__graphicsDevice.GraphicsDevice) * Resolution.getTransformationMatrix();
        }
    }
}