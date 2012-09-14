package aigilas.skills.impl;

import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import aigilas.statuses.Status;
import aigilas.statuses.StatusFactory;

public class VenomFistSkill extends ISkill {
	public VenomFistSkill()

	{
		super(SkillId.VENOM_FIST, AnimationType.SELF);

		Add(Elements.DARK);
		AddCost(StatType.MANA, 10);

	}

	@Override
	public void Activate(ICreature source) {
		super.Activate(source);
		StatusFactory.Apply(source, Status.VenomFist);

	}

}