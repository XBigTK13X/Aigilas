package aigilas.skills.animations;

import aigilas.creatures.BaseCreature;
import aigilas.entities.SkillEffect;
import sps.core.Point2;

public class RotateAnimation extends SkillAnimation {
    private Point2 rotation;
    private final Point2 location = new Point2(0, 0);

    @Override
    public void animate(SkillEffect skill, BaseCreature source, Point2 velocity) {
        if (rotation == null) {
            rotation = new Point2(source.getSkillVector().GridX, source.getSkillVector().GridY);
        }
        location.setX(rotation.GridX + source.getLocation().GridX);
        location.setY(rotation.GridY + source.getLocation().GridY);
        skill.setLocation(location);
        rotation.copy(rotation.rotate());
    }
}
