package sps.particles.behaviors;

import sps.particles.Particle2;
import sps.particles.ParticleBehavior;

public class RadiateBehavior extends ParticleBehavior {
    protected static ParticleBehavior __instance;

    public static ParticleBehavior getInstance() {
        if (__instance == null) {
            __instance = new RadiateBehavior();
        }
        return __instance;
    }

    @Override
    public void update(Particle2 particle) {
        particle.Position.setX(particle.Position.X + (float) Math.cos(particle.Angle) * particle.MoveSpeed);
        particle.Position.setY(particle.Position.Y + (float) Math.sin(particle.Angle) * particle.MoveSpeed);
    }
}
