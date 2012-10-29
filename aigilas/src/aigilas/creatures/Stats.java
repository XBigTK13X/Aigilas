package aigilas.creatures;

import sps.core.Settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stats {
    public static final int DefaultMoveDistance = Settings.get().spriteHeight;

    private HashMap<StatType, Integer> _stats = new HashMap<StatType, Integer>();
    private final List<StatBuff> _buffs = new ArrayList<StatBuff>();

    public Stats(Stats target) {
        _stats = new HashMap<StatType, Integer>(target._stats);
    }

    public Stats(int health, int mana, int strength, int wisdom, int defense, int luck, int age, int weightInLbs, int heightInFeet, int moveCoolDown, int regenRate) {
        setup(health, mana, strength, wisdom, defense, luck, age, weightInLbs, heightInFeet, moveCoolDown, 0, regenRate);
    }

    public Stats(int health, int mana, int strength, int wisdom, int defense, int luck, int age, int weightInLbs, int heightInFeet, int moveCoolDown) {
        setup(health, mana, strength, wisdom, defense, luck, age, weightInLbs, heightInFeet, moveCoolDown, 0, Settings.get().defaultRegen);
    }

    private void setup(int... list) {
        for (int ii = 0; ii < list.length; ii++) {
            _stats.put(StatType.values()[ii], list[ii]);
        }
    }

    private int statSum = 0;

    public int get(StatType stat) {
        if (_buffs != null) {
            if (!_buffs.contains(null)) {
                statSum = 0;
                for (StatBuff _buff : _buffs) {
                    if (_buff.Stat == stat) {
                        statSum += _buff.Amount;
                    }
                }
                return getRaw(stat) + statSum;
            }
        }
        return getRaw(stat);
    }

    public int getRaw(StatType stat) {
        return _stats.get(stat);
    }

    public int set(StatType stat, int value) {
        return _stats.put(stat, value);
    }

    public void addBuff(StatBuff buff) {
        if (_buffs.contains(buff)) {
            _buffs.remove(buff);
            return;
        }
        _buffs.add(buff);
    }

    private final List<Integer> deltas = new ArrayList<Integer>();

    public List<Integer> getDeltas(Stats stats) {
        deltas.clear();
        for (StatType stat : StatType.values()) {
            deltas.add(_stats.get(stat) - stats._stats.get(stat));
        }
        return deltas;
    }

    public int getBonus(int level, StatType stat) {
        return _stats.get(stat) * level;
    }

    public int getSum() {
        int result = 0;
        for (StatType key : _stats.keySet()) {
            if (key != StatType.Health && key != StatType.Move_Cool_Down && key != StatType.Regen) {
                result += _stats.get(key);
            }
        }
        return result;
    }

    String hash;

    @Override
    public int hashCode() {
        hash = "";
        for (StatType key : _stats.keySet()) {
            hash += _stats.get(key).toString();
        }
        return hash.hashCode();
    }
}
