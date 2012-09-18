package aigilas.creatures;

import aigilas.classes.CreatureClass;
import aigilas.entities.Elements;
import aigilas.management.SpriteType;
import aigilas.skills.SkillId;
import aigilas.skills.SkillPool;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;
import sps.core.Point2;

import java.util.Arrays;

public class BaseEnemy extends BaseCreature {
    public BaseEnemy(ActorType actorType, SpriteType spriteType, CreatureClass cClass) {
        SetClass(cClass);
        _actorType = actorType;
        _baseStats = new Stats(3, 1, 1, 1, 1, 1, 1, 1, 1);
        _maxStats = new Stats(_baseStats);
    }

    public BaseEnemy(ActorType actorType, SpriteType spriteType) {
        this(actorType, spriteType, null);
    }

    public BaseEnemy(ActorType actorType) {
        this(actorType, SpriteType.CREATURE, null);
    }

    public void setup(Point2 position) {
        setup(position, _actorType, _baseStats, _class);
        if (_strategy == null) {
            _strategy = StrategyFactory.create(Strategy.Attack, this, ActorType.PLAYER);
        }
    }

    protected void add(SkillId skillId) {
        if (_skills == null) {
            _skills = new SkillPool(this);
        }
        _skills.add(skillId);
    }

    float multiplier = 0f;

    protected void Strengths(StatType... stats) {
        for (StatType stat : stats) {
            multiplier = (stat == StatType.MOVE_COOL_DOWN) ? .5f : 2;
            InitStat(stat, get(stat) * multiplier);
        }
    }

    protected void Weaknesses(StatType... stats) {
        for (StatType stat : stats) {
            multiplier = (stat == StatType.MOVE_COOL_DOWN) ? 2 : .5f;
            InitStat(stat, get(stat) * multiplier);
        }
    }

    protected void Compose(Elements... elems) {
        _composition.addAll(Arrays.asList(elems));
    }
}
