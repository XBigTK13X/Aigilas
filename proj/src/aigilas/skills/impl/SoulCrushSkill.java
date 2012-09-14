package aigilas.skills.impl;

import spx.core.RNG;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;

public class SoulCrushSkill extends ISkill {
	public SoulCrushSkill()

	{
		super(SkillId.SOUL_CRUSH, AnimationType.RANGED);

		Add(Elements.MENTAL);
		AddCost(StatType.MANA, 10);

	}

	@Override
	public void Affect(ICreature target)

	{
		target.ApplyDamage(5, _source, true, StatType.values()[RNG.Next(0, 3)]);

	}

}