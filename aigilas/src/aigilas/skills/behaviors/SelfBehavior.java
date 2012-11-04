package aigilas.skills.behaviors;

import aigilas.creatures.BaseCreature;
import aigilas.entities.SkillEffect;
import aigilas.management.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import sps.core.Point2;

public class SelfBehavior extends SkillBehavior {

    public SelfBehavior(SpriteType effectGraphic, BaseSkill parentSkill) {
        super(effectGraphic, AnimationType.SELF, parentSkill);
    }

    @Override
    public void activate(BaseCreature target) {
        _sideEffects.Generate(target.getLocation(), new Point2(0, 0), target);
    }

    @Override
    public boolean affectTarget(BaseCreature source, SkillEffect graphic) {
        if (!_used) {
            source.react(_parent.getSkillId());
            _parent.affect(source);
            _used = true;
        }
        return true;
    }
}
