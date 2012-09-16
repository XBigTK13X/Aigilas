package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Pride extends AbstractCreature {
    public Pride() {
        super(ActorType.PRIDE, SpriteType.PRIDE);
        Compose(Elements.DARK);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.BREAKING_WHEEL);
    }
}
