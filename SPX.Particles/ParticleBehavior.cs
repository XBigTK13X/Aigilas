using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;
using SPX.Entities;

namespace SPX.Particles
{
    public abstract class ParticleBehavior
    {
        protected static ParticleBehavior __instance;

        protected Point2 result = new Point2(0, 0);

        public virtual int GetParticleCount()
        {
            return 10;
        }
        public abstract Point2 Update(Point2 position, Point2 origin, double angle, Particle2 particle, IEntity entity);
    }

    public class RadiateBehavior : ParticleBehavior
    {
        private float _moveSpeed = 5f;

        public override int GetParticleCount()
        {
            return 10;
        }

        public static ParticleBehavior GetInstance()
        {
            if (__instance == null)
            {
                __instance = new RadiateBehavior();
            }
            return __instance;
        }

        public override Point2 Update(Point2 position, Point2 origin, double angle, Particle2 particle, IEntity entity)
        {            
            result.SetX(position.X + (float)Math.Cos(angle) * particle.MoveSpeed);
            result.SetY(position.Y + (float)Math.Sin(angle) * particle.MoveSpeed);
            return result;
        }
    }

    public class FollowBehavior : ParticleBehavior
    {
        public override int GetParticleCount()
        {
            return 10;
        }

        public static ParticleBehavior GetInstance()
        {
            if (__instance == null)
            {
                __instance = new FollowBehavior();
            }
            return __instance;
        }

        public override Point2 Update(Point2 position, Point2 origin, double angle, Particle2 particle, IEntity entity)
        {
            if (entity != null)
            {
                result.SetX(entity.GetLocation().PosX + RNG.Rand.Next(0, GameManager.SpriteWidth / 2));
                result.SetY(entity.GetLocation().PosY + RNG.Rand.Next(0, GameManager.SpriteHeight / 2));
            }
            return result;
        }
    }
}
