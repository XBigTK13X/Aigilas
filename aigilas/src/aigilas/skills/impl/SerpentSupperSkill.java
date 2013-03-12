package aigilas.skills.impl;

import aigilas.Aigilas;
import aigilas.creatures.BaseCreature;
import aigilas.creatures.impl.CreatureFactory;
import aigilas.skills.AnimationType;
import aigilas.skills.BaseSkill;
import aigilas.skills.SkillId;
import sps.bridge.ActorTypes;
import sps.core.Point2;
import sps.core.SpsConfig;
import sps.entities.CoordVerifier;

public class SerpentSupperSkill extends BaseSkill {
    public SerpentSupperSkill()

    {
        super(SkillId.Serpent_Supper, AnimationType.SELF);


    }

    @Override
    public void activate(BaseCreature source) {
        for (int ii = 1; ii < SpsConfig.get().tileMapWidth - 1; ii++) {
            Point2 target = new Point2(ii, SpsConfig.get().tileMapHeight / 2);
            if (ii != SpsConfig.get().tileMapHeight / 2 && !CoordVerifier.isBlocked(target)) {
                CreatureFactory.create(ActorTypes.get(Aigilas.Actors.Serpent), target);
            }
        }
        for (int ii = 1; ii < SpsConfig.get().tileMapHeight - 1; ii++) {
            Point2 target = new Point2(SpsConfig.get().tileMapWidth / 2, ii);
            if (ii != SpsConfig.get().tileMapWidth / 2 && !CoordVerifier.isBlocked(target)) {
                CreatureFactory.create(ActorTypes.get(Aigilas.Actors.Serpent), target);
            }
        }

    }

}
