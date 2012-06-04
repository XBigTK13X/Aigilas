using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Entities;

namespace OGUR.Skills
{
    [Serializable]
    public class SkillComponents
    {
        protected List<int> _elements;
        protected StatBuff _buff;
        protected float _effectStrength = 0;
        protected bool _isPersistent = false;
        protected List<int> _targetTypes = new List<int>(){OGUR.EntityType.WALL};

        public SkillComponents(float strength,bool isPersistent)
        {
            _effectStrength = strength;
            _isPersistent = isPersistent;
            _elements = new List<int>() {};
        }

        public void AddElements(params int[] elements)
        {
            _elements.AddRange(elements);
        }

        public void Buff(ICreature target)
        {
            target.AddBuff(_buff);
        }

        public void SetBuff(string stat, float amount)
        {
            _buff = new StatBuff(stat, amount);
        }

        public float GetStrength()
        {
            return _effectStrength;
        }

        public bool IsPersistent()
        {
            return _isPersistent;
        }

        public List<int> GetElements()
        {
            return _elements;
        }

        public List<int> GetTargetTypes()
        {
            return _targetTypes;
        }
    }
}
