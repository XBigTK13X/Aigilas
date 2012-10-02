package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;

public class Wrath extends BaseEnemy {
    public Wrath() {
        super(ActorType.WRATH, SpriteType.WRATH);
        Compose(Elements.PHYSICAL);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.DISMEMBERMENT);
    }
}
