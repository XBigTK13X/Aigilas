package aigilas.skills.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.management.Common;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.ActorTypes;
import sps.core.Point2;
import sps.core.Settings;
import sps.entities.CoordVerifier;

public class SerpentSupperSkill extends BaseSkill {
    public SerpentSupperSkill()

    {
        super(SkillId.Serpent_Supper, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {
        for (int ii = 1; ii < Settings.get().tileMapWidth - 1; ii++) {
            Point2 target = new Point2(ii, Settings.get().tileMapHeight / 2);
            if (ii != Settings.get().tileMapHeight / 2 && !CoordVerifier.isBlocked(target)) {
                CreatureFactory.create(ActorTypes.get(Common.Serpent), target);
            }
        }
        for (int ii = 1; ii < Settings.get().tileMapHeight - 1; ii++) {
            Point2 target = new Point2(Settings.get().tileMapWidth / 2, ii);
            if (ii != Settings.get().tileMapWidth / 2 && !CoordVerifier.isBlocked(target)) {
                CreatureFactory.create(ActorTypes.get(Common.Serpent), target);
            }
        }

    }

}
