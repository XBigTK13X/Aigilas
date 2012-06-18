﻿using Aigilas.Creatures;
using Aigilas.Skills;
using SPX.Core;
using SPX.Entities;
using Aigilas.Management;
using System.Collections.Generic;
using System;
using SPX.Particles;

namespace Aigilas.Entities
{
    public class ComboMarker:Entity
    {
        private ICreature _parent;
        private int _index;

        public ComboMarker(ICreature source,int elementId,int index)
        {
            Initialize(source.GetLocation(), SpriteType.COMBO_MARKER, Aigilas.EntityType.COMBO_MARKER,ZDepth.ComboMarker);
            _graphic.SetColor(Elements.Colors[elementId]);
            _graphic.SetAlpha(0);
            ParticleEngine.Emit(SPX.Particles.RotateBehavior.GetInstance(), this, _graphic.GetColor());
            _parent = source;
            _index = index;                   
        }

        private static readonly List<Point2> __dMults = new List<Point2>()
        {
            new Point2(-1,0),
            new Point2(0,-1),
            new Point2(1,0)
        };

        public override void Update()
        {
            float dX = (GameManager.SpriteWidth / 16) * __dMults[_index].X;
            float dY = (GameManager.SpriteHeight / 16) * __dMults[_index].Y;
            SetLocation(new Point2(_parent.GetLocation().PosX + dX, _parent.GetLocation().PosY + dY));
        }

        public override void Draw()
        {
            _graphic.Draw();
        }
    }
}