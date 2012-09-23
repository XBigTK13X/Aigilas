package aigilas.skills.behaviors;

import aigilas.creatures.BaseCreature;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;

public class RangedBehavior extends SkillBehavior {
    public RangedBehavior(SpriteType effectGraphic, BaseSkill parentSkill) {
        super(effectGraphic, AnimationType.RANGED, parentSkill);
    }

    @Override
    public void activate(BaseCreature target) {
        if (SubtractCost(target)) {
            _sideEffects.Generate(target.getLocation().add(target.getSkillVector()), target.getSkillVector(), target);
        }
    }

}