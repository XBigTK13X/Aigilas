package aigilas.strategies.impl;

import aigilas.creatures.ICreature;
import aigilas.strategies.IStrategy;
import aigilas.strategies.Strategy;

public class MinionCloudStrategy extends IStrategy {
	public MinionCloudStrategy(ICreature parent)

	{
		super(parent, Strategy.MinionCloud);

		parent.SetSkillVector(parent.GetSkillVector());
	}

	@Override
	public void Act() {
		_parent.UseActiveSkill();
		_parent.SetInactive();
	}
}