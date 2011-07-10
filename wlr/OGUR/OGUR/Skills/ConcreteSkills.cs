using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.GameObjects;
using OGUR.Sprites;

namespace OGUR.Skills
{
    public class NoSkill:ISkill
    {
        public NoSkill(ICreature source):base(SkillId.NO_SKILL){}
        public override void Activate(ICreature source){}
        public override void Affect(GameplayObject target){}
    }
    public class FireballSkill:ISkill
    {

        public FireballSkill()
        {}
        public FireballSkill(ICreature source) : base(SkillId.FIREBALL)
        {
        }

        public override void Activate(ICreature source)
        {
            var direction = source.GetSkillVector();
            m_effectGraphic.Add(new SkillEffect(source.GetPosition().X, source.GetPosition().Y, direction, source, this, SpriteType.SKILL_EFFECT));
            GameplayObjectManager.AddObject(m_effectGraphic.Last());
        }

        public override void Affect(GameplayObject target)
        {
            var creature = IsCreature(target);
            if(creature!=null)
            {
                creature.ApplyDamage(20);
                if(!creature.IsActive())
                {
                    m_source.AddExperience(creature.CalculateExperience());
                }
            }
        }
    }
}