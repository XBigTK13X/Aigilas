package sps.particles;

public abstract class ParticleBehavior {
    public int getParticleCount() {
        return 10;
    }

    public abstract void update(Particle2 particle);
}
