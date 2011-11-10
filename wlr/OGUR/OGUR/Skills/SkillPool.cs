using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Skills
{
    public class SkillPool
    {
        private readonly List<string> m_skills = new List<string>();
        private int m_currentSkillSlot = 0;
        private string m_currentSkill;
        private readonly ICreature m_owner;

        public SkillPool(ICreature owner)
        {
            m_owner = owner;
            m_currentSkill=SkillId.NO_SKILL;
        }

        public void Add(string skill)
        {
            if(skill.Equals(null)&&m_skills.Count==0)
            {
                m_skills.Add(SkillId.NO_SKILL);
                FindCurrent();
                return;
            }
            m_skills.Add(skill);
            if (m_skills.Contains(SkillId.NO_SKILL))
            {
                m_skills.Remove(SkillId.NO_SKILL);
                m_currentSkillSlot = m_skills.IndexOf(skill);
            }
        }

        private string FindCurrent()
        {
            return m_currentSkill = m_skills[m_currentSkillSlot];
        }

        public void Add(IEnumerable<string> getLevelSkills)
        {
            if(!getLevelSkills.Any())
            {
                m_skills.Add(SkillId.NO_SKILL);
                return;
            }
            foreach(var skill in getLevelSkills)
            {
                m_skills.Add(skill);
            }
        }

        public void Cycle(int velocity)
        {
            m_currentSkillSlot = (m_currentSkillSlot + velocity)%m_skills.Count;
            if(m_currentSkillSlot<0)
            {
                m_currentSkillSlot = m_skills.Count() - 1;
            }
            FindCurrent();
        }

        public string GetActiveName()
        {
            return m_skills.Count>0 ? FindCurrent() : "No Skill";
        }

        private void RemoveNone()
        {
            m_skills.Remove(SkillId.NO_SKILL);
        }
        public void UseActive()
        {
            if (FindCurrent() == SkillId.NO_SKILL)
            {
                RemoveNone();
                m_currentSkillSlot = 0;
            }
            if (m_skills.Count > 0)
            {
                SkillFactory.Create(FindCurrent()).Activate(m_owner);
            }
        }
    }
}
