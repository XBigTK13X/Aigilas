package aigilas.skills.impl;

import aigilas.creatures.CreatureFactory;
import aigilas.creatures.ICreature;
import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import aigilas.skills.AnimationType;
import aigilas.skills.ISkill;
import aigilas.skills.SkillId;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.RNG;
import spx.core.Settings;

public class DismembermentSkill extends ISkill {
    public DismembermentSkill()

    {
        super(SkillId.DISMEMBERMENT, AnimationType.SELF);

        add(Elements.PHYSICAL);
        addCost(StatType.MANA, 3);

    }

    @Override
    public void activate(ICreature target)

    {
        super.activate(target);
        int openCell = RNG.next(1, Settings.get().tileMapWidth - 1);
        for (int ii = 1; ii < Settings.get().tileMapWidth - 1; ii++) {
            if (ii != openCell) {
                CreatureFactory.create(ActorType.HAND, new Point2(ii, 1));

            }

        }

    }

}
