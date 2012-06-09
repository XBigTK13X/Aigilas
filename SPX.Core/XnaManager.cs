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
        public static readonly int WindowHeight = GameManager.SpriteHeight * GameManager.TileMapHeight; //720 //1050
        public static readonly int WindowWidth = GameManager.SpriteWidth * GameManager.TileMapWidth; //1280 //1680
        public static readonly int RenderHeight = WindowHeight;
        public static readonly int RenderWidth = WindowWidth;
        private static ContentManager __assetHandler;
        private static GraphicsDeviceManager __graphics;
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

        public static void SetupCamera(ref GraphicsDeviceManager graphics,bool isFullScreen)
        {
            __graphics = graphics;
            __graphics.PreferredBackBufferHeight = XnaManager.WindowHeight;
            __graphics.PreferredBackBufferWidth = XnaManager.WindowWidth;
            __graphics.IsFullScreen = isFullScreen;
            __graphics.ApplyChanges();
            Resolution.Init(ref __graphics);
            Resolution.SetVirtualResolution(WindowWidth, WindowHeight);
            Resolution.SetResolution(RenderWidth, RenderHeight, isFullScreen);
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
            return Resolution.getTransformationMatrix();
        }
    }
}