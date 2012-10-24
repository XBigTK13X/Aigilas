package aigilas.creatures.impl;

import aigilas.creatures.BaseCreature;
import aigilas.creatures.Stats;
import aigilas.entities.SkillEffect;
import aigilas.skills.SkillId;
import aigilas.skills.SkillPool;
import sps.bridge.ActorType;
import sps.core.Point2;
import sps.core.Settings;

public class Minion extends BaseCreature {
    public Minion(ActorType actorType, int coolDown) {
        _actorType = actorType;
        //TODO Move into config
        _baseStats = new Stats(80, 999, 0, 0, 0, 0, 0, 0, 0, coolDown);
    }

    public Minion(ActorType actorType) {
        this(actorType, Settings.get().defaultSpeed);
    }

    public Minion() {
        this(ActorType.Minion, Settings.get().defaultSpeed);
    }

    public void init(BaseCreature source, SkillEffect effectGraphic) {
        _master = source;
        setup(source.getLocation(), _actorType, _baseStats, null, false);
        if (null != effectGraphic) {
            setSkillVector(effectGraphic.getDirection().rotate(180));
        }
        else {
            setSkillVector(new Point2(0, 1));
        }
        _strategy.addTargets(_master);
    }

    protected void add(SkillId skill) {
        if (_skills == null) {
            _skills = new SkillPool(this);
        }
        _skills.add(skill);
    }
}
