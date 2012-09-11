package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;

public class Zorb extends AbstractCreature {
	public Zorb() {
		super(ActorType.ZORB);
		Compose(Elements.PHYSICAL, Elements.FIRE);
		Strengths(StatType.MANA, StatType.HEALTH);
		Weaknesses(StatType.WISDOM, StatType.DEFENSE);
		Add(SkillId.FIREBALL);
	}
}
