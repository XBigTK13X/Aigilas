package spx.particles.behaviors;

import spx.particles.Particle2;
import spx.particles.ParticleBehavior;

public class RotateBehavior extends ParticleBehavior {
    protected static ParticleBehavior __instance;

    public static ParticleBehavior GetInstance() {
        if (__instance == null) {
            __instance = new RotateBehavior();
        }
        return __instance;
    }

    @Override
    public int GetParticleCount() {
        return 1;
    }

    @Override
    public void Update(Particle2 particle) {
        if (particle.Entity != null) {
            particle.Radius = 18;
            particle.SetSize(15f, 15f);
            particle.Angle += Math.PI / 30;
            particle.Position.SetX(particle.Entity.GetLocation().PosCenterX - particle.Width / 2 + (float) Math.cos(particle.Angle) * particle.Radius);
            particle.Position.SetY(particle.Entity.GetLocation().PosCenterY - particle.Height / 2 + (float) Math.sin(particle.Angle) * particle.Radius);
        }
    }
}
