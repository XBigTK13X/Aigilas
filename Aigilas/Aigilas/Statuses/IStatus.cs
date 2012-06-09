using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Agilas.Creatures;

namespace Agilas.Statuses
{
    public enum StatusComponent
    {
        Passive,
        Contagion,
        KillReward
    }    
    public class IStatus
    {
        protected IList<OAction> _prevents = new List<OAction>();

        protected bool _wasPassed = false;
        protected int _strength = 0;
        protected int _maxStrength = 100;        
        protected bool _isActive = true;
        protected ICreature _target;
        protected StatBuff _buff = null;
        protected bool _buffMax = false;
        protected IList<int> _blockedElements = new List<int>();

        protected Dictionary<StatusComponent,List<int>> _passables = new Dictionary<StatusComponent,List<int>>();

        protected IStatus(ICreature target)
        {
            _strength = _maxStrength;
            _target = target;
            Setup();
        }

        public bool IsActive(){return _isActive;}

        public bool Prevents(OAction action)
        {
            return _prevents.Any(p => p == action);
        }

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
        public virtual void Act()
        {

        }

        private void CycleBuff()
        {
            if (_buff != null)
            {
                _target.AddBuff(_buff,_buffMax);
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
