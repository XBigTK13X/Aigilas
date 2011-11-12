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

        protected int m_animation;
        protected List<SkillEffect> m_effectGraphics = new List<SkillEffect>();
        protected int m_effectSprite = SpriteType.SKILL_EFFECT;
        protected float m_effectStrength;
        protected bool m_isPersistent = false;

        public SideEffects(int effectGraphic,int animation,ISkill parent)
        {
            m_parent = parent;
            m_effectStrength = parent.GetStrength();
            m_effectSprite = effectGraphic;
            m_animation = animation;
        }

        public void Generate(Point2 gridLocation,Point2 velocity,ICreature source)
        {
            var effect = new SkillEffect(gridLocation, velocity, source, m_parent);
            m_effectGraphics.Add(effect);
            GameplayObjectManager.AddObject(effect);
        }

        public int GetSpriteType()
        {
            return m_effectSprite;
        }

        public int GetAnimationType()
        {
            return m_animation;
        }
    }    
}
