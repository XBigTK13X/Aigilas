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
        public virtual int GetParticleCount()
        {
            return 10;
        }
        public abstract void Update(Particle2 particle);
    }

    public class RadiateBehavior : ParticleBehavior
    {
        protected static ParticleBehavior __instance;
        public static ParticleBehavior GetInstance()
        {
            if (__instance == null)
            {
                __instance = new RadiateBehavior();
            }
            return __instance;
        }

        public override void Update(Particle2 particle)
        {            
            particle.Position.SetX(particle.Position.X + (float)Math.Cos(particle.Angle) * particle.MoveSpeed);
            particle.Position.SetY(particle.Position.Y + (float)Math.Sin(particle.Angle) * particle.MoveSpeed);
        }
    }

    public class FollowBehavior : ParticleBehavior
    {
        protected static ParticleBehavior __instance;
        public static ParticleBehavior GetInstance()
        {
            if (__instance == null)
            {
                __instance = new FollowBehavior();
            }
            return __instance;
        }

        public override void Update(Particle2 particle)
        {
            if (particle.Entity != null)
            {
                if (particle.Radius <= 3)
                {
                    particle.Radius = RNG.Rand.Next(20, 40);
                    particle.Angle = RNG.Angle();
                }
                particle.Radius *= particle.MoveSpeed/15;
                particle.Angle += Math.PI / 30;
                particle.Position.SetX(particle.Entity.GetLocation().PosCenterX + (float)Math.Cos(particle.Angle)*particle.Radius);
                particle.Position.SetY(particle.Entity.GetLocation().PosCenterY + (float)Math.Sin(particle.Angle)*particle.Radius);
            }
        }
    }
}
