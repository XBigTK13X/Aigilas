using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Skills
{
    public class SkillComponents
    {
        protected Stats m_cost;
        protected List<Elements> m_elements;
        protected StatBuff m_buff;
        protected float m_effectStrength = 0;
        protected bool m_isPersistent = false;

        public SkillComponents(float strength,bool isPersistent)
        {
            m_effectStrength = strength;
            m_isPersistent = isPersistent;
            m_elements = new List<Elements>() { Elements.NORMAL };
            m_cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        public void AddCost(StatType stat,float cost){
            m_cost.AddBuff(new StatBuff(stat,cost));
        }

        public void AddElements(params Elements[] elements)
        {
            m_elements.AddRange(elements);
        }

        public void Buff(ICreature target)
        {
            target.AddBuff(m_buff);
        }

        public void SetBuff(StatType stat, float amount)
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
    }
}
