package aigilas.skills.impl;

import java.util.HashMap;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class RemoteMineSkill extends ISkill {
	private static HashMap<ICreature, RemoteMineSkill> _cache = new HashMap<ICreature, RemoteMineSkill>();

	public RemoteMineSkill()

	{
		super(SkillId.REMOTE_MINE, AnimationType.STATIONARY);

		AddCost(StatType.MANA, 10);
		Add(Elements.FIRE);

	}

	@Override
	public void Activate(ICreature source) {
		if (!_cache.containsKey(source)) {
			_cache.put(source, this);
			super.Activate(source);

		}
		else {
			if (_cache.get(source).IsActive()) {
				_cache.get(source).Explode(source);
				_cache.get(source).Cleanup(source, null);
				_cache.remove(source);
				Cleanup(source, null);

			}
			else {
				_cache.remove(source);
				_cache.put(source, this);
				super.Activate(source);

			}

		}

	}

	private void Explode(ICreature source) {
		if (_behavior.GetGraphic() != null) {
			CreatureFactory.CreateMinion(SkillId.EXPLODE, _source, _behavior.GetGraphic(), _behavior.GetGraphic().GetLocation());

		}

	}

}
