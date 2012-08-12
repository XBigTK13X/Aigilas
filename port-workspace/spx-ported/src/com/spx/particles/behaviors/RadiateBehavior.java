package com.spx.particles.behaviors;

import com.spx.particles.Particle2;
import com.spx.particles.ParticleBehavior;

public class RadiateBehavior  extends  ParticleBehavior
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

    @Override
    public void Update(Particle2 particle)
    {            
        particle.Position.SetX(particle.Position.X + (float)Math.cos(particle.Angle) * particle.MoveSpeed);
        particle.Position.SetY(particle.Position.Y + (float)Math.sin(particle.Angle) * particle.MoveSpeed);
    }
}
