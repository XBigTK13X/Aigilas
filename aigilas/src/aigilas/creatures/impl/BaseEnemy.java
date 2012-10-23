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
import sps.core.Settings;

public class BaseEnemy extends BaseCreature {
    float multiplier = 0f;

    public BaseEnemy(ActorType actorType, SpriteType spriteType, CreatureClass cClass) {
        SetClass(cClass);
        _actorType = actorType;
        _baseStats = new Stats(3, 1, 1, 1, 1, 1, 1, 1, 1);
        _maxStats = new Stats(_baseStats);

        if (EnemyRegistry.get().contains(_actorType)) {
            EnemyInfo info = EnemyRegistry.get().getInfo(_actorType);
            for (StatType stat : info.Strengths) {
                multiplier = (stat == StatType.Move_Cool_Down) ? (float) 1 / Settings.get().enemyStatMultiplier : Settings.get().enemyStatMultiplier;
                InitStat(stat, get(stat) * multiplier);
            }
            for (StatType stat : info.Weaknesses) {
                multiplier = (stat == StatType.Move_Cool_Down) ? Settings.get().enemyStatMultiplier : (float) 1 / Settings.get().enemyStatMultiplier;
                InitStat(stat, get(stat) * multiplier);
            }
            for (Elements element : info.Elements) {
                _composition.add(element);
            }
            for (SkillId skill : info.Skills) {
                if (_skills == null) {
                    _skills = new SkillPool(this);
                }
                _skills.add(skill);
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
}
