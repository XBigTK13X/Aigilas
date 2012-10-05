package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.ActorType;
import sps.core.Point2;
import sps.core.RNG;
import sps.core.Settings;

public class DismembermentSkill extends BaseSkill {
    public DismembermentSkill()

    {
        super(SkillId.DISMEMBERMENT, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature target)

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
