package sps.particles;

import com.badlogic.gdx.graphics.Color;
import sps.core.Point2;
import sps.entities.Entity;

public class ParticleEngine {
    private static final Particle2[] __particles = new Particle2[1000];
    private static final Emitter[] __emitters = new Emitter[100];

    public static void reset() {
        for (int ii = 0; ii < __particles.length; ii++) {
            __particles[ii] = new Particle2();

        }
        for (int ii = 0; ii < __emitters.length; ii++) {
            __emitters[ii] = new Emitter();
            __emitters[ii].IsActive = false;
        }
    }

    private static int __emitterIndex;

    public static void emit(ParticleBehavior behavior, Point2 position, Color baseColor) {
        while (__emitters[__emitterIndex].IsActive) {
            __emitterIndex = (__emitterIndex + 1) % __emitters.length;
        }

        __emitters[__emitterIndex].reset(behavior, position, baseColor);
    }

    public static Emitter emit(ParticleBehavior behavior, Entity entity, Color baseColor) {
        while (__emitters[__emitterIndex].IsActive) {
            __emitterIndex = (__emitterIndex + 1) % __emitters.length;
        }

        __emitters[__emitterIndex].reset(behavior, entity, baseColor);
        return __emitters[__emitterIndex];
    }

    public static void update() {
        for (Emitter __emitter : __emitters) {
            __emitter.update();
        }
    }

    public static void draw() {
        for (Emitter __emitter : __emitters) {
            __emitter.draw();
        }
    }

    private static int __particleIndex;

    public static Particle2 createParticle(ParticleBehavior behavior, Point2 position, Color baseColor) {
        setIndexToInactiveParticle();
        __particles[__particleIndex].reset(behavior, position, baseColor);
        return __particles[__particleIndex];
    }

    public static Particle2 createParticle(ParticleBehavior behavior, Entity entity, Color baseColor) {
        setIndexToInactiveParticle();
        __particles[__particleIndex].reset(behavior, entity, baseColor);
        return __particles[__particleIndex];
    }

    private static void setIndexToInactiveParticle() {
        while (__particles[__particleIndex].IsActive) {
            __particleIndex = (__particleIndex + 1) % __particles.length;
        }
    }
}
