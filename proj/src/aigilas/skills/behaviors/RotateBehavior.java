package aigilas.skills.behaviors;

import aigilas.creatures.ICreature;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import spx.core.Point2;

public class RotateBehavior extends SkillBehavior {
    public RotateBehavior(SpriteType effectGraphic, ISkill parentSkill) {
        super(effectGraphic, AnimationType.ROTATE, parentSkill);
    }

    @Override
    public void activate(ICreature target) {
        _sideEffects.Generate(target.getLocation(), new Point2(0, 0), target);
    }
}
