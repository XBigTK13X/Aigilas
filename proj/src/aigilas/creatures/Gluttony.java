package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Gluttony extends BaseEnemy {
    public Gluttony() {
        super(ActorType.GLUTTONY, SpriteType.GLUTTONY);
        Compose(Elements.MENTAL);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.PLAGUE);
    }
}
