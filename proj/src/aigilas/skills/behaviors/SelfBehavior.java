package aigilas.skills.behaviors;

import aigilas.creatures.ICreature;
import aigilas.entities.SkillEffect;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import spx.core.Point2;

public class SelfBehavior extends SkillBehavior {

    public SelfBehavior(SpriteType effectGraphic, ISkill parentSkill) {
        super(effectGraphic, AnimationType.SELF, parentSkill);
    }

    @Override
    public void activate(ICreature target) {
        if (SubtractCost(target)) {
            _sideEffects.Generate(target.getLocation(), new Point2(0, 0), target);
        }
    }

    @Override
    public boolean affectTarget(ICreature source, SkillEffect graphic) {
        if (!_used) {
            source.react(_parent.getSkillId());
            _parent.affect(source);
            _used = true;
        }
        return true;
    }
}
