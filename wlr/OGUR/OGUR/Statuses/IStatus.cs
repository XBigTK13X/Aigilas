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
    public enum ActionComponent
    {
        Movement,
        Attacking,
        SkillCycle,
        Regeneration,
        HitAnything,

    }
    public class IStatus
    {
        protected bool _stopsMovement = false;
        protected bool _stopsAttacking = false;
        protected bool _stopsSkillCycling = false;
        protected bool _stopRegeneration = false;
        protected bool _hitAnything = false;
        protected bool _wasPassed = false;
        protected int _strength = 0;
        protected int _maxStrength = 100;        
        protected bool _isActive = true;
        protected ICreature _target;
        protected StatBuff _buff = null;
        protected IList<int> _blockedElements = new List<int>();

        protected Dictionary<StatusComponent,List<int>> _passables = new Dictionary<StatusComponent,List<int>>();

        protected IStatus(bool stopMovement,bool stopAttacking,ICreature target)
        {
            _stopsAttacking = stopAttacking;
            _stopsMovement = stopMovement;
            _strength = _maxStrength;
            _target = target;
            Setup();
        }

        public bool IsActive(){return _isActive;}

        public bool HitAnything() { return _hitAnything; }

        public bool StopMovement(){return _stopsMovement;}

        public bool StopAttack(){return _stopsMovement;}

        public bool StopSkillCycle() { return _stopsSkillCycling; }

        public bool StopRegeneration() { return _stopRegeneration; }

        public bool IsElementBlocked(int element)
        {
            return _blockedElements.Any(e => e == element);
        }

        public void PassOn(ICreature target,StatusComponent componentType)
        {
            if (_passables.ContainsKey(componentType))
            {
                foreach (var contagion in _passables[componentType])
                {
                    StatusFactory.Apply(target, contagion);
                }
                _wasPassed = _passables.ContainsKey(componentType);
            }
           
        }

        protected void Add(int statusId, StatusComponent componentType)
        {
            if(!_passables.ContainsKey(componentType))
            {
                _passables.Add(componentType,new List<int>());
            }
            _passables[componentType].Add(statusId);
        }

        public virtual void Update()
        {
            if (_isActive)
            {
                _strength--;
                if (_strength <= 0)
                {
                    Cleanup();
                    _isActive = false;
                }
            }
        }

        private void CycleBuff()
        {
            if (_buff != null)
            {
                _target.AddBuff(_buff);
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
