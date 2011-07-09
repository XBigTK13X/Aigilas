using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Sprites;

namespace OGUR.GameObjects
{
    class FxParticle:GameplayObject
    {
        private const double m_strengthDecayAmount = .98;
        private double m_strength;
        private Point2 m_velocity;
        private ICreature m_source;

        public FxParticle(int gridX, int gridY,Point2 velocity,double strength,ICreature source)
        {
            Initialize(gridX, gridY, SpriteType.FX_PARTICLE, GameObjectType.FX_PARTICLE);
            m_strength = strength;
            m_velocity = velocity;
            m_source = source;
        }

        public override void Update()
        {
            base.Update();
            if(m_strength<1)
            {
                m_isActive = false;
            }
            else
            {
                m_strength *= m_strengthDecayAmount;
                m_velocity.X *= m_strength;
                m_velocity.Y *= m_strength;
                Move(m_velocity.X,m_velocity.Y);
            }
        }
    }
}
