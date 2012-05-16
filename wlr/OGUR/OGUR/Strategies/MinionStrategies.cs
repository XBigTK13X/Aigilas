using OGUR.Creatures;
using SPX.Core;
using System;

namespace OGUR.Strategies
{
    public class MinionRotateStrategy : IStrategy
    {
        public MinionRotateStrategy(ICreature parent) : base(parent,Strategy.MinionRotate)//TODO pass master into strategy to copy targets here.
        {
            parent.SetSkillVector(new Point2(1,0));
        }

        public override void Act()
        {
            if (_parent.IsCooledDown())
            {
                _parent.SetSkillVector(_parent.GetSkillVector().RotateClockwise());
                _parent.UseActiveSkill();
                _parent.ApplyDamage(5, null, false);
                _parent.Set(StatType.MOVE_COOL_DOWN, 0);
            }
        }
    }
    public class MinionFireStrategy : IStrategy
    {
        public MinionFireStrategy(ICreature parent)
            : base(parent,Strategy.MinionFire)
        {
            parent.SetSkillVector(parent.GetSkillVector());
        }
        public override void Act()
        {
            if (_parent.IsCooledDown())
            {
                _parent.UseActiveSkill();
                _parent.ApplyDamage(5, null, false);
                _parent.Set(StatType.MOVE_COOL_DOWN, 0);
            }
        }
    }

    public class MinionOneUseStrategy : IStrategy
    {
        private bool f;
        public MinionOneUseStrategy(ICreature parent)
            : base(parent,Strategy.MinionOneUse)
        {
            parent.SetSkillVector(parent.GetSkillVector());
        }
        public override void Act()
        {
            _parent.UseActiveSkill();             
            _parent.SetInactive();
        }
    }

    public class MinionCloudStrategy : IStrategy
    {
        public MinionCloudStrategy(ICreature parent)
            : base(parent,Strategy.MinionCloud)
        {
            parent.SetSkillVector(parent.GetSkillVector());
        }
        public override void Act()
        {
            _parent.UseActiveSkill();
            _parent.SetInactive();
        }
    }
}