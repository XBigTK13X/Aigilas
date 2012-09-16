package aigilas.skills.behaviors;

import aigilas.creatures.ICreature;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;

public class RangedBehavior extends SkillBehavior {
    public RangedBehavior(SpriteType effectGraphic, ISkill parentSkill) {
        super(effectGraphic, AnimationType.RANGED, parentSkill);
    }

    @Override
    public void Activate(ICreature target) {
        if (SubtractCost(target)) {
            _sideEffects.Generate(target.GetLocation().Add(target.GetSkillVector()), target.GetSkillVector(), target);
        }
    }

}
