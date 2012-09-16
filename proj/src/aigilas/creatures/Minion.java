package aigilas.creatures;

import aigilas.entities.SkillEffect;
import aigilas.skills.SkillId;
import aigilas.skills.SkillPool;
import spx.bridge.ActorType;
import spx.core.Point2;
import spx.core.Settings;

public class Minion extends ICreature {
    public Minion(ActorType actorType, float coolDown) {
        _actorType = actorType;
        _baseStats = new Stats(80f, 999f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, coolDown);
    }

    public Minion(ActorType actorType) {
        this(actorType, Settings.Get().defaultSpeed);
    }

    public Minion() {
        this(ActorType.MINION, Settings.Get().defaultSpeed);
    }

    public void Init(ICreature source, SkillEffect effectGraphic) {
        _master = source;
        Setup(source.GetLocation(), _actorType, _baseStats, null, false);
        if (null != effectGraphic) {
            SetSkillVector(effectGraphic.GetDirection().Rotate180());
        } else {
            SetSkillVector(new Point2(0, 1));
        }
        _strategy.AddTargets(_master);
    }

    protected void Add(SkillId skill) {
        if (_skills == null) {
            _skills = new SkillPool(this);
        }
        _skills.Add(skill);
    }
}
