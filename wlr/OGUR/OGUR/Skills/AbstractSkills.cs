using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Sprites;

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

        public virtual void Activate(ICreature source)
        {
            m_source = source;
            m_effectGraphic.Add(UseInDirectionFaced());
        }
        public abstract void Affect(GameplayObject target);

        protected SkillEffect UseInDirectionFaced(SpriteType effectGraphic = SpriteType.SKILL_EFFECT)
        {
            var effect = new SkillEffect(m_source.GetPosition().X, m_source.GetPosition().Y, m_source.GetSkillVector(), m_source, this, SpriteType.SKILL_EFFECT);
            GameplayObjectManager.AddObject(effect);
            return effect;
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
    public class SkillId
    {
        public const string NO_SKILL = "No Skill";
        public const string FIREBALL = "Fireball";
        public const string THRASH = "Thrash";
    }
}
