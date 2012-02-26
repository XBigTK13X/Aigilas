using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Statuses
{
    public enum StatusComponent
    {
        Passive,
        Contagion,
        KillReward
    }
    public class IStatus
    {
        protected bool m_stopsMovement = false;
        protected bool m_stopsAttacking = false;
        protected bool m_hitAnything = false;
        protected bool m_wasPassed = false;
        protected int m_strength = 0;
        protected int m_maxStrength = 100;        
        protected bool m_isActive = true;
        protected ICreature m_target;
        protected StatBuff m_buff = null;
        protected Dictionary<StatusComponent,List<int>> m_passables = new Dictionary<StatusComponent,List<int>>();

        protected IStatus(bool stopMovement,bool stopAttacking,ICreature target)
        {
            m_stopsAttacking = stopAttacking;
            m_stopsMovement = stopMovement;
            m_strength = m_maxStrength;
            m_target = target;
            Setup();
        }

        public bool HitAnything()
        {
            return m_hitAnything;
        }

        public bool IsActive()
        {
            return m_isActive;
        }

        public bool StopMovement()
        {
            return m_stopsMovement;
        }

        public bool StopAttack()
        {
            return m_stopsMovement;
        }

        public void PassOn(ICreature target,StatusComponent componentType)
        {
            if (m_passables.ContainsKey(componentType))
            {
                foreach (var contagion in m_passables[componentType])
                {
                    StatusFactory.Apply(target, contagion);
                }
                m_wasPassed = m_passables.ContainsKey(componentType);
            }
           
        }

        protected void Add(int statusId, StatusComponent componentType)
        {
            if(!m_passables.ContainsKey(componentType))
            {
                m_passables.Add(componentType,new List<int>());
            }
            m_passables[componentType].Add(statusId);
        }

        public virtual void Update()
        {
            if (m_isActive)
            {
                m_strength--;
                if (m_strength <= 0)
                {
                    Cleanup();
                    m_isActive = false;
                }
            }
        }

        private void CycleBuff()
        {
            if (m_buff != null)
            {
                m_target.AddBuff(m_buff);
            }
        }

        public virtual void Setup() 
        {
            CycleBuff();
        }
        public virtual void Cleanup() 
        {
            CycleBuff();
        }
    }
}
