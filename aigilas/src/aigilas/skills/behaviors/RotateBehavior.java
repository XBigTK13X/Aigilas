package aigilas.skills.behaviors;

import aigilas.creatures.BaseCreature;
import aigilas.energygement.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import sps.core.Point2;

public class RotateBehavior extends SkillBehavior {
    public RotateBehavior(SpriteType effectGraphic, BaseSkill parentSkill) {
        super(effectGraphic, AnimationType.ROTATE, parentSkill);
    }

    @Override
    public void activate(BaseCreature target) {
        _sideEffects.Generate(target.getLocation(), new Point2(0, 0), target);
    }
}
