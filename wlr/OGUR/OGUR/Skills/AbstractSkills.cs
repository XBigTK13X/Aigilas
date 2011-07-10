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
        protected Stats m_cost;
        protected ICreature m_source;
        protected List<Elements> m_elements = new List<Elements>() {Elements.NORMAL};
        public static ISkill NULL;
        protected SkillId m_implementationId;
        protected List<SkillEffect> m_effectGraphic = new List<SkillEffect>();

        protected ISkill()
        {}

        protected ISkill(SkillId implementationId)
        {
            m_elements = new List<Elements>(){Elements.NORMAL};
            m_cost = new Stats(0,0,0,0,0,0,0,0,0,0,0);
            m_implementationId = implementationId;
        }

        protected ISkill(IEnumerable<Elements> elements, Stats cost)
        {
            m_elements = new List<Elements>(elements);
            m_cost = new Stats(cost);
        }

        public override string ToString()
        {
            return ImplEnumToString(m_implementationId);
        }

        public abstract void Activate(ICreature source);
        public abstract void Affect(GameplayObject target);

        public void SetSource(ICreature source)
        {
            m_source = source;
        }

        public static string ImplEnumToString(SkillId skill)
        {
            switch(skill)
            {
                case SkillId.FIREBALL:
                    return "Fireball";
                case SkillId.NO_SKILL:
                    return "No Skill";
                default:
                    return "No Name Defined in ImplEnumToString!";
            }
        }
        public ICreature IsCreature(GameplayObject target)
        {
            if(target.GetObjectType()==GameObjectType.CREATURE)
            {
                return (ICreature) target;
            }
            return null;
        }
    }
    public enum SkillId
    {
        NO_SKILL,
        FIREBALL
    }

}
