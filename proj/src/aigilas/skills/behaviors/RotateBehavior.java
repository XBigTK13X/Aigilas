package aigilas.skills.behaviors;

import spx.core.Point2;
import aigilas.creatures.ICreature;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;

public class RotateBehavior extends SkillBehavior {
	public RotateBehavior(SpriteType effectGraphic, ISkill parentSkill) {
		super(effectGraphic, AnimationType.ROTATE, parentSkill);
	}

	@Override
	public void Activate(ICreature target) {
		_sideEffects.Generate(target.GetLocation(), new Point2(0, 0), target);
	}
}