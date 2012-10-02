package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;

public class Envy extends BaseEnemy {
    public Envy() {
        super(ActorType.ENVY, SpriteType.ENVY);
        Compose(Elements.WATER);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.HYPOTHERMIA);
    }
}
