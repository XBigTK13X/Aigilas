using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Skills
{
    public class SkillPool
    {
        private List<ISkill> m_skills = new List<ISkill>();
        private int m_currentSkillSlot = 0;

        public SkillPool()
        {
        }

        public void Add(ISkill skill)
        {
            if(skill.Equals(null)&&m_skills.Count==0)
            {
                m_skills.Add(new NoSkill());
                return;
            }
            m_skills.Add(skill);
        }

        public void Add(List<ISkill> getLevelSkills)
        {
            if(getLevelSkills.Count==0)
            {
                m_skills.Add(new NoSkill());
                return;
            }
            m_skills.AddRange(getLevelSkills);
        }

        public void Cycle(int velocity)
        {
            m_currentSkillSlot = (m_currentSkillSlot + velocity)%m_skills.Count;
        }

        public ISkill GetActive()
        {
            if(m_skills.Count>0)
            {
                return m_skills[m_currentSkillSlot];    
            }
            return ISkill.NULL;
        }
    }
}
