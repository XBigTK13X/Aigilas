package aigilas.entities;

import aigilas.creatures.ICreature;
import aigilas.management.SpriteType;
import spx.bridge.DrawDepth;
import spx.bridge.EntityType;
import spx.core.Point2;
import spx.core.Settings;
import spx.entities.Entity;
import spx.particles.ParticleEngine;

import java.util.Arrays;
import java.util.List;

public class ComboMarker extends Entity {
    private ICreature _parent;
    private int _index;

    public ComboMarker(ICreature source, Elements elementId, int index) {
        initialize(source.getLocation(), SpriteType.COMBO_MARKER, EntityType.COMBO_MARKER, DrawDepth.ComboMarker);
        _graphic.setColor(elementId.Tint);
        _graphic.setAlpha(0);
        ParticleEngine.emit(spx.particles.behaviors.RotateBehavior.getInstance(), this, _graphic.getColor());
        _parent = source;
        _index = index;
    }

    private static List<Point2> __dMults = Arrays.asList(new Point2(-1, 0), new Point2(0, -1), new Point2(1, 0));

    @Override
    public void update() {
        float dX = (Settings.get().spriteWidth / 16) * __dMults.get(_index).X;
        float dY = (Settings.get().spriteHeight / 16) * __dMults.get(_index).Y;
        setLocation(new Point2(_parent.getLocation().PosX + dX, _parent.getLocation().PosY + dY));
    }

    @Override
    public void draw() {
        _graphic.draw();
    }
}
