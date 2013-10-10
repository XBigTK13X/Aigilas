package sps.particles;

import com.badlogic.gdx.graphics.Color;
import sps.bridge.SpriteType;
import sps.core.Point2;
import sps.entities.Entity;

public class Emitter extends PEComponent {
    private final Particle2[] _particles = new Particle2[10];
    private int _index = 0;
    ParticleBehavior _behavior;

    public Emitter() {
    }

    public void update() {
        if (IsActive) {
            IsActive = false;
            for (int ii = 0; ii < _index; ii++) {
                if (_particles[ii].IsActive) {
                    IsActive = true;
                    _particles[ii].update();
                }
            }
        }
    }

    public void draw() {
        if (IsActive) {
            for (int ii = 0; ii < _index; ii++) {
                _particles[ii].draw();
            }
        }
    }

    public void reset(ParticleBehavior behavior, Point2 position, Color baseColor) {
        IsActive = true;
        _behavior = behavior;
        _index = 0;
        while (_index < behavior.getParticleCount()) {
            _particles[_index] = ParticleEngine.createParticle(behavior, position, baseColor);
            _index++;
        }
    }

    public void reset(ParticleBehavior behavior, Entity entity, Color baseColor) {
        IsActive = true;
        _behavior = behavior;
        _index = 0;
        while (_index < behavior.getParticleCount()) {
            _particles[_index] = ParticleEngine.createParticle(behavior, entity, baseColor);
            _index++;
        }
    }

    public void clear() {
        for (Particle2 p : _particles) {
            if (p != null) {
                p.clear();
            }
        }
    }

    public void setSprite(SpriteType sprite) {
        for (int ii = 0; ii < _index; ii++) {
            _particles[ii].setSprite(sprite);
        }
    }
}
