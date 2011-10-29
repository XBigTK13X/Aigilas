using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Management;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    public class GameplayObject
    {
        protected AnimatedTexture m_graphic = new AnimatedTexture();

        protected bool m_isActive = true;
        protected bool m_isBlocking = false;
        protected SpriteType m_assetName;
        protected GameObjectType m_objectType;
        protected bool m_isOnBoard = true;
        protected List<Elements> m_composition = new List<Elements>(){Elements.NORMAL};
        private bool m_isInteracting = false;
        protected Point2 m_location = new Point2(0,0);

        //Load the texture for the sprite using the Content Pipeline
        public void LoadContent()
        {
            m_graphic.LoadContent(m_assetName);
        }

        //Draw the sprite to the screen
        public virtual void Draw()
        {
            if(m_isOnBoard)
            {
                m_graphic.Draw();                
            }
        }

        public void Hide()
        {
            m_isOnBoard = false;
        }

        public void Show()
        {
            m_isOnBoard = true;
        }

        protected void Initialize(Point2 location, SpriteType spriteType, GameObjectType objectType)
        {
            m_assetName = spriteType;
            m_objectType = objectType;
            m_location.Copy(location);
            m_graphic.SetPosition(m_location);
        }

        public virtual void Update()
        {
        }

        public void SetLocation(Point2 location)
        {
            m_graphic.SetPosition(location);
            m_location = location;
        }

        public void UpdateLocation(Point2 location)
        {
            var oldLocation = m_location;
            m_graphic.SetPosition(location);
            m_location = location;
            GameplayObjectManager.UpdateGridLocation(this, oldLocation);
        }

        public void Move(float amountX, float amountY)
        {
            amountX = NormalizeDistance(amountX);
            amountY = NormalizeDistance(amountY);
            var target = new Point2(m_location.PosX + amountX,m_location.PosY + amountY);
            if (CoordVerifier.IsValid(target))
            {
                UpdateLocation(target);
            }
        }

        private static float NormalizeDistance(float amount)
        {
            var isNeg = (amount < 0)? -1:1;
            amount = Math.Abs(amount);
            var factorsOfSpriteHeight = (int)Math.Floor(amount/SpriteInfo.Height);
            factorsOfSpriteHeight = (factorsOfSpriteHeight == 0 && amount!=0) ? 1 : factorsOfSpriteHeight;
            return (SpriteInfo.Height*factorsOfSpriteHeight*isNeg);
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

        public Point2 GetLocation()
        {
            return m_location;
        }

        public bool IsGraphicLoaded()
        {
            return (m_graphic != null);
        }

        protected void SetSpriteInfo(SpriteInfo sprite)
        {
            m_graphic.SetSpriteInfo(sprite);
        }

        public bool Contains(Point2 target)
        {
            return target.GridX == GetLocation().GridX && target.GridY == GetLocation().GridY;
        }

        public void SetInteraction(bool isInteracting)
        {
            m_isInteracting = isInteracting;
        }

        public bool IsInteracting()
        {
            return m_isInteracting;
        }

        public void PerformInteraction()
        {
            m_isInteracting = false;
            InputManager.Lock(InputManager.Commands.Confirm, ((ICreature) this).GetPlayerIndex());
        }

        public ICreature IsCreature()
        {
            if (GetObjectType() == GameObjectType.CREATURE)
            {
                return (ICreature)this;
            }
            return null;
        }
    }
}