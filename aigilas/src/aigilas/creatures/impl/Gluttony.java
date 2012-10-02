package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;

public class Gluttony extends BaseEnemy {
    public Gluttony() {
        super(ActorType.GLUTTONY, SpriteType.GLUTTONY);
        Compose(Elements.MENTAL);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.PLAGUE);
    }
}
