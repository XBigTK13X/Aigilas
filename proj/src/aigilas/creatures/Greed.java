package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Greed extends AbstractCreature {
    public Greed() {
        super(ActorType.GREED, SpriteType.GREED);
        Compose(Elements.AIR);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        add(SkillId.BOIL);
    }
}
