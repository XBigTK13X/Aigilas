using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Entities;
using OGUR.Management;
using SPX.Entities;
using OGUR.Creatures;
using SPX.Core;

namespace OGUR.Skills
{
    public class SideEffects
    {
        protected ISkill _parent;

        protected int _animation;
        protected List<SkillEffect> _effectGraphics = new List<SkillEffect>();
        protected int _effectSprite = SpriteType.SKILL_EFFECT;
        protected float _effectStrength;
        protected bool _isPersistent = false;

        public SideEffects(int effectGraphic,int animation,ISkill parent)
        {
            _parent = parent;
            _effectStrength = parent.GetStrength();
            _effectSprite = effectGraphic;
            _animation = animation;
        }

        public void Generate(Point2 gridLocation,Point2 velocity,ICreature source)
        {
            var effect = new SkillEffect(gridLocation, velocity, source, _parent);
            _effectGraphics.Add(effect);
            EntityManager.AddObject(effect);
        }

        public SkillEffect GetFirstGraphic()
        {
            if(_effectGraphics.Count()>0)
                return _effectGraphics[0];
            return null;
        }

        public int GetSpriteType()
        {
            return _effectSprite;
        }

        public int GetAnimationType()
        {
            return _animation;
        }
    }    
}
