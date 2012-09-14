package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class AcidDripSkill extends ISkill {
	public AcidDripSkill()

	{
		super(SkillId.ACID_DRIP, AnimationType.STATIONARY);

		StartOffCenter = true;
		AddCost(StatType.MANA, 10);
		Add(Elements.WATER);

	}

	@Override
	public void Affect(ICreature target)

	{
		target.ApplyDamage(20, _source);

	}

}