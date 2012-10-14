package aigilas.creatures.impl;

import aigilas.classes.CreatureClass;
import aigilas.creatures.*;
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
        if (EnemyRegistry.get().contains(_actorType)) {
            EnemyInfo info = EnemyRegistry.get().getInfo(_actorType);
            for (StatType stat : info.Strengths) {
                strengths(stat);
            }
            for (StatType stat : info.Weaknesses) {
                weaknesses(stat);
            }
            for (Elements element : info.Elements) {
                compose(element);
            }
            for (SkillId skill : info.Skills) {
                add(skill);
            }
        }
    }

    public BaseEnemy(ActorType actorType, SpriteType spriteType) {
        this(actorType, spriteType, null);
    }

    public BaseEnemy(ActorType actorType) {
        this(actorType, SpriteType.Creature, null);
    }

    public void setup(Point2 position) {
        setup(position, _actorType, _baseStats, _class);
        if (_strategy == null) {
            _strategy = StrategyFactory.create(Strategy.Attack, this, ActorType.Player);
        }
    }

    protected void add(SkillId skillId) {
        if (_skills == null) {
            _skills = new SkillPool(this);
        }
        _skills.add(skillId);
    }

    float multiplier = 0f;

    protected void strengths(StatType... stats) {
        for (StatType stat : stats) {
            multiplier = (stat == StatType.Move_Cool_Down) ? .5f : 2;
            InitStat(stat, get(stat) * multiplier);
        }
    }

    protected void weaknesses(StatType... stats) {
        for (StatType stat : stats) {
            multiplier = (stat == StatType.Move_Cool_Down) ? 2 : .5f;
            InitStat(stat, get(stat) * multiplier);
        }
    }

    protected void compose(Elements... elems) {
        _composition.addAll(Arrays.asList(elems));
    }
}
