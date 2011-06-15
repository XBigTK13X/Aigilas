using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Collision;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    class GameplayObject
    {

        protected AnimatedTexture m_graphic = new AnimatedTexture(); 
       
        protected bool m_isActive = true;
        protected bool m_isBlocking = false;
        protected SpriteType m_assetName;
        protected GameObjectType m_objectType;
        
        //Load the texture for the sprite using the Content Pipeline
        public void LoadContent()
        {
            m_graphic.LoadContent(m_assetName);
        }
        //Draw the sprite to the screen
        public void Draw()
        {
            m_graphic.Draw();
        }

        protected void Initialize(int x, int y, SpriteType spriteType,GameObjectType objectType)
        {
            m_assetName = spriteType;
            m_objectType = objectType;
            m_graphic.SetPosition(x,y);
        }
        public virtual void Update()
        {
        }
        public void Move(int amountX, int amountY)
        {
            if (CoordVerifier.IsValid((int)m_graphic.GetPosition().X + amountX,(int)m_graphic.GetPosition().Y + amountY))
            {
                m_graphic.SetPosition((int)m_graphic.GetPosition().X + amountX, (int)m_graphic.GetPosition().Y + amountY);
            }
        }
        public void SetPosition(int x, int y)
        {
            if (CoordVerifier.IsValid(x, y))
            {
                m_graphic.SetPosition(x, y);
            }
        }
        public bool IsActive()
        {
            return m_isActive;
        }
        public void SetInactive()
        {
            m_isActive = false;
        }
        public bool IsBlocking()
        {
            return m_isBlocking;
        }
        public SpriteType GetAssetType()
        {
            return m_assetName;
        }
        public GameObjectType GetObjectType()
        {
            return m_objectType;
        }
        public Vector2 GetPosition()
        {
            return m_graphic.GetPosition();
        }
        public bool IsGraphicLoaded()
        {
            return (m_graphic != null);
        }
        protected void SetSpriteInfo(SpriteInfo sprite)
        {
            m_graphic.SetSpriteInfo(sprite);
        }

        public bool Contains(OGUR.Collision.Point target)
        {
            return (target.X >= GetPosition().X) && (target.Y >= GetPosition().Y) && (target.X <= GetPosition().X + SpriteInfo.Width) && (target.Y <= GetPosition().Y + SpriteInfo.Height);
        }
    }
}