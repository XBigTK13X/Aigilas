package aigilas.creatures.impl;

import aigilas.Aigilas;
import aigilas.Config;
import aigilas.creatures.BaseCreature;
import aigilas.creatures.StatsRegistry;
import aigilas.entities.SkillEffect;
import aigilas.skills.SkillId;
import aigilas.skills.SkillPool;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;
import sps.core.Point2;

public class Minion extends BaseCreature {
    public Minion(ActorType actorType, int coolDown) {
        _actorType = actorType;
        _baseStats = StatsRegistry.get().baseStats(ActorTypes.get(Aigilas.Actors.Minion));
    }

    public Minion(ActorType actorType) {
        this(actorType, Config.get().defaultSpeed);
    }

    public Minion() {
        this(ActorTypes.get(Aigilas.Actors.Minion), Config.get().defaultSpeed);
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
        _strategies.current().addTargets(_master);
    }

    protected void add(SkillId skill) {
        if (_skills == null) {
            _skills = new SkillPool(this);
        }
        _skills.add(skill);
    }
}
