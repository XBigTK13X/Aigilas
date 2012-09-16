package aigilas.creatures;

import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;

public class Zorb extends AbstractCreature {
    public Zorb() {
        super(ActorType.ZORB);
        Compose(Elements.PHYSICAL, Elements.FIRE);
        Strengths(StatType.MANA, StatType.HEALTH);
        Weaknesses(StatType.WISDOM, StatType.DEFENSE);
        add(SkillId.FIREBALL);
    }
}
