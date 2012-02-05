using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Dungeons;
using OGUR.Paths;

namespace OGUR.Strategies
{
    public class MinionRotate : IStrategy
    {
        public MinionRotate(ICreature parent) : base(parent)//TODO pass master into strategy to copy targets here.
        {
            parent.SetSkillVector(new Point2(1,0));
        }

        public override void Act()
        {
            if (m_parent.Get(StatType.MOVE_COOL_DOWN) >= m_parent.GetMax(StatType.MOVE_COOL_DOWN))
            {
                m_parent.SetSkillVector(m_parent.GetSkillVector().RotateClockwise());
                m_parent.UseActiveSkill();
                m_parent.ApplyDamage(5, null, false);
                m_parent.Set(StatType.MOVE_COOL_DOWN, 0);
            }
        }
    }
    public class MinionFire : IStrategy
    {
        public MinionFire(ICreature parent)
            : base(parent)
        {
            parent.SetSkillVector(parent.GetSkillVector());
        }
        public override void Act()
        {
            if (m_parent.Get(StatType.MOVE_COOL_DOWN) >= m_parent.GetMax(StatType.MOVE_COOL_DOWN))
            {
                m_parent.UseActiveSkill();
                m_parent.ApplyDamage(5, null, false);
                m_parent.Set(StatType.MOVE_COOL_DOWN, 0);
            }
        }
    }

    public class MinionExplode : IStrategy
    {
        public MinionExplode(ICreature parent)
            : base(parent)
        {
            parent.SetSkillVector(parent.GetSkillVector());
        }
        public override void Act()
        {
            m_parent.UseActiveSkill();
            m_parent.SetInactive();   
        }
    }

    public class MinionCloud : IStrategy
    {
        public MinionCloud(ICreature parent)
            : base(parent)
        {
            parent.SetSkillVector(parent.GetSkillVector());
        }
        public override void Act()
        {
            m_parent.UseActiveSkill();
            m_parent.SetInactive();
        }
    }
}