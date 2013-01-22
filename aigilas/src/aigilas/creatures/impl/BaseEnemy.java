package aigilas.creatures.impl;

import aigilas.Config;
import aigilas.classes.CreatureClass;
import aigilas.creatures.*;
import aigilas.entities.Elements;
import aigilas.skills.SkillId;
import aigilas.skills.SkillPool;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import sps.bridge.ActorType;
import sps.bridge.ActorTypes;
import sps.bridge.Sps;
import sps.core.Point2;

public class BaseEnemy extends BaseCreature {
    float multiplier = 0f;

    public BaseEnemy(ActorType actorType, CreatureClass cClass) {
        SetClass(cClass);
        _actorType = actorType;
        _baseStats = StatsRegistry.get().baseStats(ActorTypes.get(Sps.ActorGroups.Non_Player));
        _maxStats = new Stats(_baseStats);

        if (EnemyRegistry.get().contains(_actorType)) {
            EnemyInfo info = EnemyRegistry.get().getInfo(_actorType);
            for (StatType stat : info.Strengths) {
                initStat(stat, get(stat) + Config.get().enemyStatMultiplier);
            }
            for (StatType stat : info.Weaknesses) {
                initStat(stat, get(stat) - Config.get().enemyStatMultiplier);
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


    public BaseEnemy(ActorType actorType) {
        this(actorType, null);
    }

    public void setup(Point2 position) {
        setup(position, _actorType, _baseStats, _class);
        if (_strategies == null) {
            setStrategy(StrategyFactory.create(Strategy.Attack, this, ActorTypes.get(Sps.Actors.Player)));
        }
    }
}
