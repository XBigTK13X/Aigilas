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
                if (particle.Radius < 5)
                {
                    particle.Radius = 5;
                    particle.Toggle = true;
                }
                else if (particle.Radius > 16)
                {
                    particle.Radius = 16;
                    particle.Toggle = false;
                }
                if (particle.Toggle)
                {
                    particle.Radius += particle.MoveSpeed/10;
                    particle.Angle += Math.PI / 300;
                }
                else
                {
                    particle.Radius -= particle.MoveSpeed/10;
                    particle.Angle -= Math.PI / 150;
                }

                particle.Position.SetX(particle.Entity.GetLocation().PosCenterX - particle.Width / 2 + (float)Math.Cos(particle.Angle) * particle.Radius);
                particle.Position.SetY(particle.Entity.GetLocation().PosCenterY - particle.Height / 2 + (float)Math.Sin(particle.Angle) * particle.Radius);
            }
        }
    }

    public class RotateBehavior : ParticleBehavior
    {
        protected static ParticleBehavior __instance;
        public static ParticleBehavior GetInstance()
        {
            if (__instance == null)
            {
                __instance = new RotateBehavior();
            }
            return __instance;
        }

        public override int GetParticleCount()
        {
            return 1;
        }

        public override void Update(Particle2 particle)
        {
            if (particle.Entity != null)
            {
                particle.Radius = 18;
                particle.SetSize(15, 15);
                particle.Angle += Math.PI / 30;
                particle.Position.SetX(particle.Entity.GetLocation().PosCenterX - particle.Width/2 + (float)Math.Cos(particle.Angle) * particle.Radius);
                particle.Position.SetY(particle.Entity.GetLocation().PosCenterY -particle.Height/2 + (float)Math.Sin(particle.Angle) * particle.Radius);
            }
        }
    }
}
