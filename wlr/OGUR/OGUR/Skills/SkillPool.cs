using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Skills
{
    public class SkillPool
    {
        private readonly List<string> _skills = new List<string>();
        private int _currentSkillSlot = 0;
        private string _currentSkill;
        private readonly ICreature _owner;
        private Dictionary<string, int> _usageCounter = new Dictionary<string, int>();
        private Dictionary<int, string> _hotSkills = new Dictionary<int, string>();

        public SkillPool(ICreature owner)
        {
            _owner = owner;
            _currentSkill=SkillId.NO_SKILL;
        }

        public void Add(string skill)
        {
            if(skill.Equals(null)&&_skills.Count==0)
            {
                _skills.Add(SkillId.NO_SKILL);
                FindCurrent();
                return;
            }
            if (!_skills.Contains(skill))
            {
                _skills.Add(skill);
            }
            if (_skills.Contains(SkillId.NO_SKILL))
            {
                _skills.Remove(SkillId.NO_SKILL);
                _currentSkillSlot = _skills.IndexOf(skill);
            }
        }

        private string FindCurrent()
        {
            return _currentSkill = _skills[_currentSkillSlot];
        }

        public void Add(IEnumerable<string> getLevelSkills)
        {
            if(!getLevelSkills.Any())
            {
                _skills.Add(SkillId.NO_SKILL);
                return;
            }
            foreach(var skill in getLevelSkills)
            {
                if (!_skills.Contains(skill))
                {
                    _skills.Add(skill);
                }
            }
        }

        public void Cycle(int velocity)
        {
            _currentSkillSlot = (_currentSkillSlot + velocity)%_skills.Count;
            if(_currentSkillSlot<0)
            {
                _currentSkillSlot = _skills.Count() - 1;
            }
            FindCurrent();
        }

        public string GetActiveName()
        {
            return _skills.Count>0 ? FindCurrent() : "No Skill";
        }

        private void RemoveNone()
        {
            _skills.Remove(SkillId.NO_SKILL);
        }
        public void UseActive()
        {
            if (FindCurrent() == SkillId.NO_SKILL)
            {
                RemoveNone();
                _currentSkillSlot = 0;
            }
            if (_skills.Count > 0)
            {
                UseSkill(FindCurrent());
            }
        }

        private void UseSkill(string skillId)
        {
            SkillFactory.Create(FindCurrent()).Activate(_owner);
            if (!_usageCounter.ContainsKey(skillId))
            {
                _usageCounter.Add(skillId, 0);
            }
            _usageCounter[skillId]++;
        }

        string _leastUsed;
        public void RemoveLeastUsed()
        {
            foreach (var skillId in _skills)
            {
                if (!_usageCounter.ContainsKey(skillId))
                {
                    _usageCounter.Add(skillId, 0);
                }
            }
            _leastUsed = null;
            foreach (var keyValue in _usageCounter)
            {
                if ((_leastUsed == null || _leastUsed == SkillId.FORGET_SKILL || _usageCounter[keyValue.Key] < _usageCounter[_leastUsed]) 
                     && keyValue.Key != SkillId.FORGET_SKILL)
                {
                    _leastUsed = keyValue.Key;
                }
            }
            if (_leastUsed != SkillId.FORGET_SKILL)
            {
                _skills.Remove(_leastUsed);
            }
        }

        public int Count()
        {
            return _skills.Count();
        }

        public void MakeActiveSkillHot(int hotSkillSlot)
        {
            if (!_hotSkills.ContainsKey(hotSkillSlot))
            {
                _hotSkills.Add(hotSkillSlot, GetActiveName());
            }
            _hotSkills[hotSkillSlot] = GetActiveName();
        }

        public bool SetHotSkillsActive(int hotkey)
        {
            if (_hotSkills.ContainsKey(hotkey))
            {
                for (int ii = 0; ii < _skills.Count(); ii++)
                {
                    if (_skills[ii] == _hotSkills[hotkey])
                    {
                        _currentSkillSlot = ii;
                        FindCurrent();
                        return true;
                    }
                }
            }
            return false;
        }

        internal IList<string> GetHotSkillNames()
        {
            return _hotSkills.Values.ToList();
        }
    }
}
