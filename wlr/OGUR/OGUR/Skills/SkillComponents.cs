using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Entities;

namespace OGUR.Skills
{
    public class SkillComponents
    {
        protected List<int> m_elements;
        protected StatBuff m_buff;
        protected float m_effectStrength = 0;
        protected bool m_isPersistent = false;
        protected List<int> m_targetTypes = new List<int>(){EntityType.WALL};

        public SkillComponents(float strength,bool isPersistent)
        {
            m_effectStrength = strength;
            m_isPersistent = isPersistent;
            m_elements = new List<int>() {};
        }

        public void AddElements(params int[] elements)
        {
            m_elements.AddRange(elements);
        }

        public void Buff(ICreature target)
        {
            target.AddBuff(m_buff);
        }

        public void SetBuff(string stat, float amount)
        {
            m_buff = new StatBuff(stat, amount);
        }

        public float GetStrength()
        {
            return m_effectStrength;
        }

        public bool IsPersistent()
        {
            return m_isPersistent;
        }

        public List<int> GetElements()
        {
            return m_elements;
        }

        public List<int> GetTargetTypes()
        {
            return m_targetTypes;
        }
    }
}
