using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using SPX.Sprites;
using SPX.Core;
using System.Runtime.Serialization;

namespace SPX.Entities
{
    public class Entity: IEntity
    {
        protected AnimatedTexture _graphic = new AnimatedTexture();

        protected bool _isActive = true;
        protected bool? _isBlocking;
        protected int _assetName;
        protected int _objectType;
        protected bool _isOnBoard = true;
        private bool _isInteracting = false;
        protected Point2 _location = new Point2(0,0);
        protected int _entityType;

        public Entity() { }

        public Entity(SerializationInfo info, StreamingContext context)
        {
            _graphic = (AnimatedTexture)info.GetValue("Entity.Graphic",typeof(AnimatedTexture));
            _isBlocking = (bool)info.GetValue("Entity.IsBlocking", typeof(bool));
            _assetName = (int)info.GetValue("Entity.AssetName", typeof(int));
            _objectType = (int)info.GetValue("Entity.ObjectType",typeof(bool));
            _isOnBoard = (bool)info.GetValue("Entity.IsOnBoard",typeof(bool));
            _isInteracting = (bool)info.GetValue("Entity.IsInteracting",typeof(bool));
            _location = (Point2)info.GetValue("Entity.Location",typeof(Point2));
            _entityType = (int)info.GetValue("Entity.EntityType",typeof(int));
        }

        public void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            info.AddValue("Entity.Graphic", _graphic);
            info.AddValue("Entity.IsBlocking", _isBlocking);
            info.AddValue("Entity.AssetName", _assetName);
            info.AddValue("Entity.ObjectType", _objectType);
            info.AddValue("Entity.IsOnBoard", _isOnBoard);
            info.AddValue("Entity.IsInteracting", _isInteracting);
            info.AddValue("Entity.Location", _location);
            info.AddValue("Entity.EntityType", _entityType);            
        }

        public void LoadContent()
        {
            _graphic.LoadContent(_assetName);
        }

        public virtual void Draw()
        {
            if(_isOnBoard && _isActive)
            {
                _graphic.Draw();                
            }
        }

        public void Hide()
        {
            _isOnBoard = false;
        }

        public void Show()
        {
            _isOnBoard = true;
        }

        protected void Initialize(Point2 location, int spriteType, int objectType,float depth = 0f)
        {
            _assetName = spriteType;
            _objectType = objectType;
            _location.Copy(location);
            _graphic.SetPosition(_location);
            _graphic.SetDepth(depth);
        }

        public virtual void Update()
        {
        }

        public void SetLocation(Point2 location)
        {
            _graphic.SetPosition(location);
            _location.Copy(location);
        }

        private Point2 oldLocation = new Point2(0, 0);
        public void UpdateLocation(Point2 location)
        {
            oldLocation.Copy(_location);
            _graphic.SetPosition(location);
            _location.Copy(location);
            EntityManager.UpdateGridLocation(this, oldLocation);
        }

        private Point2 target = new Point2(0, 0);
        public bool Move(float amountX, float amountY)
        {
            amountX = NormalizeDistance(amountX);
            amountY = NormalizeDistance(amountY);
            target.Reset(_location.PosX + amountX,_location.PosY + amountY);
            if (CoordVerifier.IsValid(target))
            {
                UpdateLocation(target);
                return true;
            }
            return false;
        }

        private static int isNeg = 1;
        private static int factorsOfSpriteHeight = 0;
        private static float NormalizeDistance(float amount)
        {
            isNeg = (amount < 0)? -1:1;
            amount = Math.Abs(amount);
            factorsOfSpriteHeight = (int)Math.Floor(amount / GameManager.SpriteHeight);
            factorsOfSpriteHeight = (factorsOfSpriteHeight == 0 && amount!=0) ? 1 : factorsOfSpriteHeight;
            return (GameManager.SpriteHeight * factorsOfSpriteHeight * isNeg);
        }

        public bool IsActive()
        {
            return _isActive;
        }

        public void SetInactive()
        {
            _isActive = false;
        }

        public bool IsBlocking()
        {
            return _isBlocking??false;
        }

        public int GetAssetType()
        {
            return _assetName;
        }

        public Point2 GetLocation()
        {
            return _location;
        }

        public bool IsGraphicLoaded()
        {
            return (_graphic != null);
        }

        protected void SetSpriteInfo(SpriteInfo sprite)
        {
            _graphic.SetSpriteInfo(sprite);
        }

        public bool Contains(Point2 target)
        {
            return target.GridX == GetLocation().GridX && target.GridY == GetLocation().GridY;
        }

        public void SetInteraction(bool isInteracting)
        {
            _isInteracting = isInteracting;
        }

        public bool IsInteracting()
        {
            return _isInteracting;
        }

        public void SetInteracting(bool isInteracting)
        {
            _isInteracting = isInteracting;
        }

        public int GetEntityType()
        {
            return _objectType;
        }        
    }
}