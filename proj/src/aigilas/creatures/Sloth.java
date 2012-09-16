package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Sloth extends AbstractCreature {
    public Sloth() {
        super(ActorType.SLOTH, SpriteType.SLOTH);
        Compose(Elements.EARTH);
        Strengths(StatType.STRENGTH, StatType.STRENGTH);
        Add(SkillId.SERPENT_SUPPER);
    }
}
