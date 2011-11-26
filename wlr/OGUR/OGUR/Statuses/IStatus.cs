using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Statuses
{
    public class IStatus
    {
        protected bool m_stopsMovement = false;
        protected bool m_stopsAttacking = false;
        protected bool m_hitAnything = false;
        protected int m_strength = 0;
        protected int m_maxStrength = 100;        
        protected bool m_isActive = true;
        protected ICreature m_target;

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

        public virtual void Setup() { }
        public virtual void Cleanup() { }
    }
}
