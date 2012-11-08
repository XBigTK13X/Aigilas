package aigilas.entities;

import aigilas.creatures.BaseCreature;
import aigilas.management.Common;
import sps.bridge.DrawDepths;
import sps.bridge.EntityTypes;
import sps.entities.Entity;
import sps.particles.Emitter;
import sps.particles.ParticleEngine;

public class ReactionMarker extends Entity {
    private final BaseCreature _parent;
    private final Emitter emitter;

    public ReactionMarker(BaseCreature source, Elements elementId) {
        initialize(source.getLocation(), null, EntityTypes.get(Common.Combo_Marker), DrawDepths.get(Common.Combo_Marker));
        _graphic.setColor(elementId.Tint);
        _graphic.setAlpha(0);
        _parent = source;
        emitter = ParticleEngine.emit(sps.particles.behaviors.RotateBehavior.getInstance(), _parent, _graphic.getColor());
    }

    @Override
    public void update() {
    }

    @Override
    public void draw() {
    }

    @Override
    public void setInactive() {
        super.setInactive();
        emitter.clear();
    }
}
