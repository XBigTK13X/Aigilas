package aigilas.skills;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import spx.bridge.ActorType;
import spx.bridge.EntityType;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatBuff;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;

public class SkillComponents {
	protected List<Elements> _elements;
	protected StatBuff _buff;
	protected float _effectStrength = 0;
	protected boolean _isPersistent = false;
	protected List<EntityType> _targetTypes = Arrays.asList(EntityType.WALL);
	protected List<ActorType> _targetActorTypes = new ArrayList<>();

	public SkillComponents(float strength, boolean isPersistent) {
		_effectStrength = strength;
		_isPersistent = isPersistent;
		_elements = new ArrayList<>();
	}

	public void AddElements(Elements... elements) {
		for (Elements element : elements) {
			_elements.add(element);
		}
	}

	public void Buff(ICreature target) {
		target.AddBuff(_buff);
	}

	public void SetBuff(StatType stat, float amount) {
		_buff = new StatBuff(stat, amount);
	}

	public float GetStrength() {
		return _effectStrength;
	}

	public boolean IsPersistent() {
		return _isPersistent;
	}

	public List<Elements> GetElements() {
		return _elements;
	}

	public List<EntityType> GetTargetTypes()

	{
		return _targetTypes;
	}
}
