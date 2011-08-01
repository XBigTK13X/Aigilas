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
        protected string m_implementationId;
        protected List<SkillEffect> m_effectGraphic = new List<SkillEffect>();

        protected ISkill(string implementationId, Stats cost = null, IEnumerable<Elements> elements = null)
        {
            m_elements = elements==null ? new List<Elements>() { Elements.NORMAL } : new List<Elements>(elements);
            m_cost = cost==null ? new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) : new Stats(cost);
            m_implementationId = implementationId;
        }

        public override string ToString()
        {
            return m_implementationId;
        }

        public abstract void Activate(ICreature source);
        public abstract void Affect(GameplayObject target);

        public ICreature IsCreature(GameplayObject target)
        {
            if(target.GetObjectType()==GameObjectType.CREATURE)
            {
                return (ICreature) target;
            }
            return null;
        }
    }
    public class SkillId
    {
        public const string NO_SKILL = "No Skill";
        public const string FIREBALL = "Fireball";
        public const string THRASH = "Thrash";
    }
}
