package aigilas.skills.animations;

import aigilas.creatures.BaseCreature;
import aigilas.entities.SkillEffect;
import spx.core.Point2;

public class SelfAnimation extends SkillAnimation {
    @Override
    public void animate(SkillEffect skill, BaseCreature source, Point2 velocity) {
        skill.updateLocation(source.getLocation());
    }
}
