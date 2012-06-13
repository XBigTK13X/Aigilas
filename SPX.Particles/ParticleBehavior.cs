using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SPX.Core;

namespace SPX.Particles
{
    public abstract class ParticleBehavior
    {
        protected static ParticleBehavior __instance;

        protected Point2 result = new Point2(0, 0);

        public abstract int GetParticleCount();
        public abstract Point2 Update(Point2 position, Point2 origin, double angle,Particle2 particle);
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

        public override Point2 Update(Point2 position, Point2 origin, double angle,Particle2 particle)
        {            
            result.SetX(position.X + (float)Math.Cos(angle) * particle.MoveSpeed);
            result.SetY(position.Y + (float)Math.Sin(angle) * particle.MoveSpeed);
            return result;
        }
    }
}
