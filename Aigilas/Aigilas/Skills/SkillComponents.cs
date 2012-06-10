using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Aigilas.Creatures;
using Aigilas.Entities;

namespace Aigilas.Skills
{
    public class SkillComponents
    {
        protected List<int> _elements;
        protected StatBuff _buff;
        protected float _effectStrength = 0;
        protected bool _isPersistent = false;
        protected List<int> _targetTypes = new List<int>(){Aigilas.EntityType.WALL};

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
