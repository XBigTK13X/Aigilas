package spx.particles.behaviors;

import spx.core.RNG;
import spx.particles.Particle2;
import spx.particles.ParticleBehavior;

public class FollowBehavior extends ParticleBehavior {
	protected static ParticleBehavior __instance;

	public static ParticleBehavior GetInstance() {
		if (__instance == null) {
			__instance = new FollowBehavior();
		}
		return __instance;
	}

	@Override
	public void Update(Particle2 particle) {
		if (particle.Entity != null) {

			float size = RNG.Next(0, 10);
			particle.SetSize(size, size);
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

			particle.Position.SetX(particle.Entity.GetLocation().PosCenterX - particle.Width / 2 + (float) Math.cos(particle.Angle) * particle.Radius);
			particle.Position.SetY(particle.Entity.GetLocation().PosCenterY - particle.Height / 2 + (float) Math.sin(particle.Angle) * particle.Radius);
		}
	}
}
