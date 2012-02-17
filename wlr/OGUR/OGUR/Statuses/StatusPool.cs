using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Statuses
{
    public class StatusPool
    {
        private List<IStatus> m_statuses = new List<IStatus>();

        public bool CanMove()
        {
            for (int ii = 0; ii < m_statuses.Count(); ii++)
            {
                if (m_statuses[ii].StopMovement())
                {
                    return false;
                }
            }
            return true;
        }

        public bool CanAttack()
        {
            for (int ii = 0; ii < m_statuses.Count(); ii++)
            {
                if (m_statuses[ii].StopAttack())
                {
                    return false;
                }
            }
            return true;
        }

        public bool WillHitAnything()
        {
            for (int ii = 0; ii < m_statuses.Count(); ii++)
            {
                if (m_statuses[ii].HitAnything())
                {
                    return true;
                }
            }
            return false;
        }

        public void Add(IStatus status)
        {
            m_statuses.Add(status);
        }

        public void Update()
        {
            for (int ii = 0; ii < m_statuses.Count(); ii++)
            {
                m_statuses[ii].Update();
                if (!m_statuses[ii].IsActive())
                {
                    m_statuses.Remove(m_statuses[ii]);
                    ii--;
                }
            }
        }

        internal void ApplyContagions(ICreature target)
        {
            foreach (var status in m_statuses)
            {
                status.ApplyContagion(target);
            }
        }
    }
}
