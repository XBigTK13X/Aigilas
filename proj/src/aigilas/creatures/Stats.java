package aigilas.creatures;

import spx.core.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stats {
    public static float DefaultMoveDistance = Settings.Get().spriteHeight;

    private HashMap<StatType, Float> _stats = new HashMap<>();
    private final List<StatBuff> _buffs = new ArrayList<StatBuff>();

    public Stats(Stats target) {
        _stats = new HashMap<StatType, Float>(target._stats);
    }

    public Stats(float health, float mana, float strength, float wisdom, float defense, float luck, float age, float weightInLbs, float heightInFeet, float moveCoolDown, float regenRate) {

        Setup(health, mana, strength, wisdom, defense, luck, age, weightInLbs, heightInFeet, moveCoolDown, 0, regenRate);
    }

    public Stats(float health, float mana, float strength, float wisdom, float defense, float luck, float age, float weightInLbs, float heightInFeet, float moveCoolDown) {
        Setup(health, mana, strength, wisdom, defense, luck, age, weightInLbs, heightInFeet, moveCoolDown, 0, Settings.Get().defaultRegen);
    }

    public Stats(float health, float mana, float strength, float wisdom, float defense, float luck, float age, float weightInLbs, float heightInFeet) {
        Setup(health, mana, strength, wisdom, defense, luck, age, weightInLbs, heightInFeet, Settings.Get().defaultSpeed, 0, Settings.Get().defaultRegen);

    }

    private void Setup(float... list) {
        for (int ii = 0; ii < list.length; ii++) {
            _stats.put(StatType.values()[ii], list[ii]);
        }
    }

    private float statSum = 0;

    public float Get(StatType stat) {
        if (_buffs != null) {
            if (!_buffs.contains(null)) {
                statSum = 0;
                for (int ii = 0; ii < _buffs.size(); ii++) {
                    if (_buffs.get(ii).Stat == stat) {
                        statSum += _buffs.get(ii).Amount;
                    }
                }
                return GetRaw(stat) + statSum;
            }
        }
        return GetRaw(stat);
    }

    public float GetRaw(StatType stat) {
        return _stats.get(stat);
    }

    public float Set(StatType stat, float value) {
        return _stats.put(stat, value);
    }

    public void AddBuff(StatBuff buff) {
        if (_buffs.contains(buff)) {
            _buffs.remove(buff);
            return;
        }
        _buffs.add(buff);
    }

    private final List<Float> deltas = new ArrayList<Float>();

    public List<Float> GetDeltas(Stats stats) {
        deltas.clear();
        for (StatType stat : StatType.values()) {
            deltas.add(_stats.get(stat) - stats._stats.get(stat));
        }
        return deltas;
    }

    public Stats GetLevelBonuses(int level) {
        Stats result = new Stats(this);
        for (StatType stat : result._stats.keySet()) {
            result._stats.put(stat, _stats.get(stat) + result._stats.get(stat) * level);
        }
        return result;
    }

    public float GetBonus(int level, StatType stat) {
        return _stats.get(stat) * level;
    }

    public float GetSum() {
        float result = 0;
        for (StatType key : _stats.keySet()) {
            if (key != StatType.HEALTH && key != StatType.MOVE_COOL_DOWN && key != StatType.REGEN) {
                result += _stats.get(key);
            }
        }
        return result;
    }

    Float hash = 0f;

    @Override
    public int hashCode() {
        hash = 0f;
        for (StatType key : _stats.keySet()) {
            hash += _stats.get(key);
        }
        return hash.hashCode();
    }
}
