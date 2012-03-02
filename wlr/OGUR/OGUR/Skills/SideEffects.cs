using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Entities;
using SPX.Sprites;
using SPX.Entities;
using OGUR.Creatures;
using SPX.Core;

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
            EntityManager.AddObject(effect);
        }

        public SkillEffect GetFirstGraphic()
        {
            if(m_effectGraphics.Count()>0)
                return m_effectGraphics[0];
            return null;
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
