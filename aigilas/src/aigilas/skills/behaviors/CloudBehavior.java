package aigilas.skills.behaviors;

import aigilas.creatures.BaseCreature;
import sps.bridge.SpriteType;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import sps.core.Point2;

public class CloudBehavior extends SkillBehavior {
    public CloudBehavior(SpriteType effectGraphic, BaseSkill parentSkill) {
        super(effectGraphic, AnimationType.CLOUD, parentSkill);
    }

    @Override
    public void activate(BaseCreature target) {
        Point2 referencePoint = target.getLocation();
        for (int ii = -1; ii < 2; ii++) {
            for (int jj = -1; jj < 2; jj++) {
                if (ii != 0 || jj != 0) {
                    Point2 cloudPosition = new Point2(referencePoint.GridX + ii, referencePoint.GridY + jj);
                    _sideEffects.Generate(cloudPosition, new Point2(0, 0), target);
                }
            }
        }
    }
}
