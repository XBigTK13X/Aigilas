package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;

public class Zorb extends BaseEnemy {
    public Zorb() {
        super(ActorType.ZORB);
        Compose(Elements.PHYSICAL, Elements.FIRE);
        Strengths(StatType.MANA, StatType.HEALTH);
        Weaknesses(StatType.WISDOM, StatType.DEFENSE);
        add(SkillId.FIREBALL);
    }
}
