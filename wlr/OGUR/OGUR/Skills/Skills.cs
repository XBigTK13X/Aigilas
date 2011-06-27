using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;

namespace OGUR.Skills
{
    public abstract class ISkill
    {
        private Stats m_cost;
        private List<Elements> m_elements = new List<Elements>() {Elements.NORMAL};

        protected ISkill(IEnumerable<Elements> elements,Stats cost)
        {
            m_elements = new List<Elements>(elements);
            m_cost = new Stats(cost);
        }

        protected abstract void Activate(ICreature target);
    }

    public class Bludgeon:ISkill
    {
        public Bludgeon(IEnumerable<Elements> elements, Stats cost):base(elements,cost)
        {
        }

        protected override void Activate(ICreature target)
        {
        }
    }
}
