using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Collision;
using OGUR.Creatures;

namespace OGUR.Skills
{
    public class SideEffects
    {
        protected ISkill m_parent;

        protected Skill.Animation m_animation;
        protected List<SkillEffect> m_effectGraphics = new List<SkillEffect>();
        protected SpriteType m_effectSprite = SpriteType.SKILL_EFFECT;
        protected float m_effectStrength;
        protected bool m_isPersistent = false;
        protected Stats m_cost;

        public SideEffects(SpriteType effectGraphic,Skill.Animation animation,ISkill parent)
        {
            m_parent = parent;
            m_effectStrength = parent.GetStrength();
            m_effectSprite = effectGraphic;
            m_animation = animation;
            m_cost = new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        }

        public void AddCost(StatType stat, float cost)
        {
            m_cost.AddBuff(new StatBuff(stat, cost));
        }

        public void Generate(Point2 gridLocation,Point2 velocity,ICreature source)
        {
            if (SubtractCost(source))
            {
                var effect = new SkillEffect(gridLocation, velocity, source, m_parent);
                m_effectGraphics.Add(effect);
                GameplayObjectManager.AddObject(effect);
            }
        }

        public SpriteType GetSpriteType()
        {
            return m_effectSprite;
        }

        public Skill.Animation GetAnimationType()
        {
            return m_animation;
        }

        private bool SubtractCost(ICreature owner)
        {
            bool costPaid = false;
            foreach (StatType stat in Enum.GetValues(typeof(StatType)))
            {
                if (owner.LowerStat(stat, m_cost.Get(stat)))
                {
                    costPaid = true;
                }
            }
            return costPaid;
        }
    }    
}
