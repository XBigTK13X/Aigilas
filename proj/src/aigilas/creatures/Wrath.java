package aigilas.creatures;

import spx.bridge.ActorType;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;

public class Wrath extends AbstractCreature {
	public Wrath() {
		super(ActorType.WRATH, SpriteType.WRATH);
		Compose(Elements.PHYSICAL);
		Strengths(StatType.STRENGTH, StatType.STRENGTH);
		Add(SkillId.DISMEMBERMENT);
	}
}
