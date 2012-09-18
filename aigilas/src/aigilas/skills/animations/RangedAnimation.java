package aigilas.skills.animations;

import aigilas.creatures.BaseCreature;
import aigilas.entities.SkillEffect;
import sps.core.Point2;

public class RangedAnimation extends SkillAnimation {
    @Override
    public void animate(SkillEffect skill, BaseCreature source, Point2 velocity) {
        if (!skill.move(velocity.X, velocity.Y)) {
            skill.cleanup(skill);
        }
    }
}
