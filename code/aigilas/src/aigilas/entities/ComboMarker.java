package aigilas.entities;import java.util.Arrays;import java.util.List;import spx.bridge.DrawDepth;import spx.bridge.EntityType;import spx.core.Point2;import spx.core.Settings;import spx.entities.Entity;import spx.particles.ParticleEngine;import aigilas.creatures.ICreature;import aigilas.management.SpriteType;public class ComboMarker extends Entity {	private ICreature _parent;	private int _index;	public ComboMarker(ICreature source, int elementId, int index) {		Initialize(source.GetLocation(), SpriteType.COMBO_MARKER, EntityType.COMBO_MARKER, DrawDepth.ComboMarker);		_graphic.SetColor(Elements.Colors[elementId]);		_graphic.SetAlpha(0);		ParticleEngine.Emit(spx.particles.behaviors.RotateBehavior.GetInstance(), this, _graphic.GetColor());		_parent = source;		_index = index;	}	private static List<Point2> __dMults = Arrays.asList(new Point2(-1, 0), new Point2(0, -1), new Point2(1, 0));	@Override	public void Update() {		float dX = (Settings.Get().spriteWidth / 16) * __dMults.get(_index).X;		float dY = (Settings.Get().spriteHeight / 16) * __dMults.get(_index).Y;		SetLocation(new Point2(_parent.GetLocation().PosX + dX, _parent.GetLocation().PosY + dY));	}	@Override	public void Draw() {		_graphic.Draw();	}}