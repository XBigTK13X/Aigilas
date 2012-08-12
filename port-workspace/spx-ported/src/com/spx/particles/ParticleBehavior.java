package com.spx.particles;import com.spx.wrapper.*;import java.util.*;import com.spx.core.*;import com.spx.entities.*;public abstract class ParticleBehavior
{
    public int GetParticleCount()
    {
        return 10;
    }
    public abstract void Update(Particle2 particle);
}