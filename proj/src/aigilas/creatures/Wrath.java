package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Wrath extends AbstractCreature {
    public Wrath() {
        super(ActorType.WRATH, SpriteType.WRATH);
        Compose(Elements.PHYSICAL);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.DISMEMBERMENT);
    }
}
