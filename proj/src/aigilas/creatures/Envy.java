package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Envy extends BaseEnemy {
    public Envy() {
        super(ActorType.ENVY, SpriteType.ENVY);
        Compose(Elements.WATER);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.HYPOTHERMIA);
    }
}
