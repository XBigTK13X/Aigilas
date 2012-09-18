package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.CreatureFactory;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.Settings;

public class SerpentSupperSkill extends BaseSkill {
    public SerpentSupperSkill()

    {
        super(SkillId.SERPENT_SUPPER, AnimationType.SELF);

        add(Elements.MENTAL);
        addCost(StatType.MANA, 10);

    }

    @Override
    public void activate(BaseCreature source) {
        for (int ii = 1; ii < Settings.get().tileMapWidth - 1; ii++) {
            if (ii != Settings.get().tileMapHeight / 2) {
                CreatureFactory.create(ActorType.SERPENT, new Point2(ii, Settings.get().tileMapHeight / 2));

            }

        }
        for (int ii = 1; ii < Settings.get().tileMapHeight - 1; ii++) {
            if (ii != Settings.get().tileMapWidth / 2) {
                CreatureFactory.create(ActorType.SERPENT, new Point2(Settings.get().tileMapWidth / 2, ii));

            }

        }

    }

}
