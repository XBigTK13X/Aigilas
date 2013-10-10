package sps.particles.behaviors;

import sps.core.RNG;
import sps.particles.Particle2;
import sps.particles.ParticleBehavior;

public class FollowBehavior extends ParticleBehavior {
    protected static ParticleBehavior __instance;

    public static ParticleBehavior getInstance() {
        if (__instance == null) {
            __instance = new FollowBehavior();
        }
        return __instance;
    }

    @Override
    public void update(Particle2 particle) {
        if (particle.Entity != null) {

            float size = RNG.next(0, 10);
            particle.setSize(size, size);
            if (particle.Radius < 5) {
                particle.Radius = 5;
                particle.Toggle = true;
            }
            else if (particle.Radius > 16) {
                particle.Radius = 16;
                particle.Toggle = false;
            }
            if (particle.Toggle) {
                particle.Radius += particle.MoveSpeed / 10;
                particle.Angle += Math.PI / 300;
            }
            else {
                particle.Radius -= particle.MoveSpeed / 10;
                particle.Angle -= Math.PI / 150;
            }

            particle.Position.setX(particle.Entity.getLocation().PosCenterX - particle.Width / 2 + (float) Math.cos(particle.Angle) * particle.Radius);
            particle.Position.setY(particle.Entity.getLocation().PosCenterY - particle.Height / 2 + (float) Math.sin(particle.Angle) * particle.Radius);
        }
    }
}
