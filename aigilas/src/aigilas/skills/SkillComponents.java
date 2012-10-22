package aigilas.skills;

import aigilas.creatures.StatBuff;
import aigilas.entities.Elements;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.ActorType;
import sps.bridge.EntityType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SkillComponents {
    protected final List<Elements> _elements;
    protected StatBuff _buff;
    protected float _effectStrength = 0;
    protected boolean _isPersistent = false;
    protected final List<EntityType> _targetTypes = Arrays.asList(EntityType.Wall);
    protected List<ActorType> _targetActorTypes = new ArrayList<ActorType>();
    protected EntityType _onlyAffects;

    public SkillComponents(float strength, boolean isPersistent) {
        _effectStrength = strength;
        _isPersistent = isPersistent;
        _elements = new ArrayList<Elements>();
    }

    public void addElements(Elements... elements) {
        Collections.addAll(_elements, elements);
    }

    public float getStrength() {
        return _effectStrength;
    }

    public boolean isPersistent() {
        return _isPersistent;
    }

    public List<Elements> getElements() {
        return _elements;
    }

    public Color getColor() {
        return _elements.get(0).Tint;
    }

    public void setOnlyAffects(EntityType type) {
        _onlyAffects = type;
    }

    public boolean onlyAffects(EntityType type) {
        return type == _onlyAffects || _onlyAffects == null;
    }

    public List<EntityType> getTargetTypes() {
        return _targetTypes;
    }
}
