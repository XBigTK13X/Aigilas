package aigilas.skills.behaviors;

import aigilas.creatures.BaseCreature;
import sps.core.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import sps.core.Point2;

public class StationaryBehavior extends SkillBehavior {
    public StationaryBehavior(SpriteType effectGraphic, BaseSkill parentSkill) {

        super(effectGraphic, AnimationType.STATIONARY, parentSkill);
    }

    @Override
    public void activate(BaseCreature target) {
        if (_parent.getSkillId().Info.OffCenter) {
            Point2 location = new Point2(target.getLocation().GridX + target.getSkillVector().GridX, target.getLocation().GridY + target.getSkillVector().GridY);
            _sideEffects.Generate(location, new Point2(0, 0), target);
        }
        else {
            _sideEffects.Generate(target.getLocation(), new Point2(0, 0), target);
        }
    }
}
