package aigilas.skills;

import aigilas.creatures.BaseCreature;
import aigilas.management.Commands;
import sps.core.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillPool {
    private final List<SkillId> _skills = new ArrayList<SkillId>();
    private int _currentSkillSlot = 0;
    private final BaseCreature _owner;
    private final HashMap<SkillId, Integer> _usageCounter = new HashMap<SkillId, Integer>();
    private final HashMap<Commands, SkillId> _hotSkills = new HashMap<Commands, SkillId>();

    public SkillPool(BaseCreature owner) {
        _owner = owner;
    }

    public void add(SkillId skill) {
        if (skill == null && _skills.size() == 0) {
            _skills.add(SkillId.NO_SKILL);
            findCurrent();
            return;
        }
        if (!_skills.contains(skill)) {
            _skills.add(skill);
        }
        if (_skills.contains(SkillId.NO_SKILL)) {
            _skills.remove(SkillId.NO_SKILL);
            _currentSkillSlot = _skills.indexOf(skill);
        }
    }

    private SkillId findCurrent() {
        return _skills.get(_currentSkillSlot);
    }

    public void add(List<SkillId> getLevelSkills) {
        if (getLevelSkills.size() == 0) {
            _skills.add(SkillId.NO_SKILL);
            return;
        }
        for (SkillId skill : getLevelSkills) {
            if (!_skills.contains(skill)) {
                _skills.add(skill);
            }
        }
    }

    public void cycle(int velocity) {
        try {
            _currentSkillSlot = (_currentSkillSlot + velocity) % _skills.size();
            if (_currentSkillSlot < 0) {
                _currentSkillSlot = _skills.size() - 1;
            }
            findCurrent();
        }
        catch (Exception e) {
            int x = 0;
        }
    }

    public String getActiveName() {
        return _skills.size() > 0 ? findCurrent().Info.Name : SkillId.NO_SKILL.Info.Name;
    }

    private void removeNone() {
        _skills.remove(SkillId.NO_SKILL);
    }

    public void useActive() {
        if (findCurrent() == SkillId.NO_SKILL) {
            removeNone();
            _currentSkillSlot = 0;
        }
        if (_skills.size() > 0) {
            useSkill(findCurrent());
        }
    }

    private void useSkill(SkillId skillId) {
        SkillFactory.create(findCurrent()).activate(_owner);
        Logger.gameplay(_owner.toString() + " used " + findCurrent().toString());
        if (!_usageCounter.containsKey(skillId)) {
            _usageCounter.put(skillId, 0);
        }
        _usageCounter.put(skillId, _usageCounter.get(skillId) + 1);
    }

    SkillId _leastUsed;

    public void removeLeastUsed() {
        for (SkillId skillId : _skills) {
            if (!_usageCounter.containsKey(skillId)) {
                _usageCounter.put(skillId, 0);
            }
        }
        _leastUsed = null;
        for (SkillId key : _usageCounter.keySet()) {
            if ((_leastUsed == null || _leastUsed == SkillId.FORGET_SKILL || _usageCounter.get(key) < _usageCounter.get(_leastUsed)) && key != SkillId.FORGET_SKILL) {
                _leastUsed = key;
            }
        }
        if (_leastUsed != SkillId.FORGET_SKILL) {
            _skills.remove(_leastUsed);
        }
    }

    public int count() {
        return _skills.size();
    }

    public void makeActiveSkillHot(Commands hotSkillSlot) {
        if (!_hotSkills.containsKey(hotSkillSlot)) {
            _hotSkills.put(hotSkillSlot, findCurrent());
        }
        _hotSkills.put(hotSkillSlot, findCurrent());
    }

    public boolean setHotSkillsActive(Commands hotkey) {
        if (_hotSkills.containsKey(hotkey)) {
            for (int ii = 0; ii < _skills.size(); ii++) {
                if (_skills.get(ii) == _hotSkills.get(hotkey)) {
                    _currentSkillSlot = ii;
                    findCurrent();
                    return true;
                }
            }
        }
        return false;
    }

    public String getHotSkillName(Commands hotSkillSlot) {
        if (_hotSkills.containsKey(hotSkillSlot)) {
            return _hotSkills.get(hotSkillSlot).Info.Name;
        }
        return SkillId.NO_SKILL.Info.Name;
    }

    public float getCurrentCost() {
        return SkillFactory.getCost(findCurrent());
    }

    public SkillId getActive() {
        return findCurrent();
    }
}
