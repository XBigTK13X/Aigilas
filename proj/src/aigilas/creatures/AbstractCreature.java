package aigilas.creatures;

import java.util.Arrays;

import spx.bridge.ActorType;
import spx.core.Point2;
import aigilas.classes.CreatureClass;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import aigilas.skills.SkillPool;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

public class AbstractCreature extends ICreature {
	public AbstractCreature(ActorType actorType, SpriteType spriteType, CreatureClass cClass) {
		SetClass(cClass);
		_actorType = actorType;
		_baseStats = new Stats(3, 1, 1, 1, 1, 1, 1, 1, 1);
		_maxStats = new Stats(_baseStats);
	}

	public AbstractCreature(ActorType actorType, SpriteType spriteType) {
		this(actorType, spriteType, null);
	}

	public AbstractCreature(ActorType actorType) {
		this(actorType, SpriteType.CREATURE, null);
	}

	public void Setup(Point2 position) {
		Setup(position, _actorType, _baseStats, _class);
		if (_strategy == null) {
			_strategy = StrategyFactory.Create(Strategy.Attack, this, ActorType.PLAYER);
		}
	}

	protected void Add(SkillId skillId) {
		if (_skills == null) {
			_skills = new SkillPool(this);
		}
		_skills.Add(skillId);
	}

	float multiplier = 0f;

	protected void Strengths(StatType... stats) {
		for (StatType stat : stats) {
			multiplier = (stat == StatType.MOVE_COOL_DOWN) ? .5f : 2;
			InitStat(stat, Get(stat) * multiplier);
		}
	}

	protected void Weaknesses(StatType... stats) {
		for (StatType stat : stats) {
			multiplier = (stat == StatType.MOVE_COOL_DOWN) ? 2 : .5f;
			InitStat(stat, Get(stat) * multiplier);
		}
	}

	protected void Compose(Elements... elems) {
		_composition.addAll(Arrays.asList(elems));
	}
}
