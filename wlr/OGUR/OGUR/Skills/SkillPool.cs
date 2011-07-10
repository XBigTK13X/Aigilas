using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Skills
{
    public class SkillPool
    {
        private List<ISkill> m_skills = new List<ISkill>();
        private int m_currentSkillSlot = 0;
        private ISkill m_currentSkill;
        private ICreature m_owner;

        public SkillPool(ICreature owner)
        {
            m_owner = owner;
            m_currentSkill=new NoSkill(m_owner);
        }

        public void Add(ISkill skill)
        {
            if(skill.Equals(null)&&m_skills.Count==0)
            {
                m_skills.Add(new NoSkill(m_owner));
                FindCurrent();
                return;
            }
            m_skills.Add(skill);
        }

        private ISkill FindCurrent()
        {
            return m_currentSkill = m_skills[m_currentSkillSlot];
        }

        public void Add(List<ISkill> getLevelSkills)
        {
            if(getLevelSkills.Count==0)
            {
                m_skills.Add(new NoSkill(m_owner));
                return;
            }
            foreach(var skill in getLevelSkills)
            {
                skill.SetSource(m_owner);
                m_skills.Add(skill);
            }
        }

        public void Cycle(int velocity)
        {
            m_currentSkillSlot = (m_currentSkillSlot + velocity)%m_skills.Count;
            FindCurrent();
        }

        public string GetActiveName()
        {
            if(m_skills.Count>0)
            {
                return FindCurrent().ToString();    
            }
            return "No Skill";
        }

        public void UseActive()
        {
            if (m_skills.Count > 0)
            {
                FindCurrent().Activate(m_owner);
            }
        }
    }
}
