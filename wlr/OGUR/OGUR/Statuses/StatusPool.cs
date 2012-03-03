using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Statuses
{
    public class StatusPool
    {
        private List<IStatus> _statuses = new List<IStatus>();

        public bool CanMove()
        {
            for (int ii = 0; ii < _statuses.Count(); ii++)
            {
                if (_statuses[ii].StopMovement())
                {
                    return false;
                }
            }
            return true;
        }

        public bool CanAttack()
        {
            for (int ii = 0; ii < _statuses.Count(); ii++)
            {
                if (_statuses[ii].StopAttack())
                {
                    return false;
                }
            }
            return true;
        }

        public bool WillHitAnything()
        {
            for (int ii = 0; ii < _statuses.Count(); ii++)
            {
                if (_statuses[ii].HitAnything())
                {
                    return true;
                }
            }
            return false;
        }

        public void Add(IStatus status)
        {
            _statuses.Add(status);
        }

        public void Update()
        {
            for (int ii = 0; ii < _statuses.Count(); ii++)
            {
                _statuses[ii].Update();
                if (!_statuses[ii].IsActive())
                {
                    _statuses.Remove(_statuses[ii]);
                    ii--;
                }
            }
        }

        public void PassOn(ICreature target,StatusComponent componentType)
        {
            for (int ii = 0; ii < _statuses.Count; ii++)
            {
                _statuses[ii].PassOn(target, componentType);
            }
        }
    }
}
