using OGUR.Creatures;
using SPX.Core;

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
            if (_parent.Get(StatType.MOVE_COOL_DOWN) >= _parent.GetMax(StatType.MOVE_COOL_DOWN))
            {
                _parent.SetSkillVector(_parent.GetSkillVector().RotateClockwise());
                _parent.UseActiveSkill();
                _parent.ApplyDamage(5, null, false);
                _parent.Set(StatType.MOVE_COOL_DOWN, 0);
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
            if (_parent.Get(StatType.MOVE_COOL_DOWN) >= _parent.GetMax(StatType.MOVE_COOL_DOWN))
            {
                _parent.UseActiveSkill();
                _parent.ApplyDamage(5, null, false);
                _parent.Set(StatType.MOVE_COOL_DOWN, 0);
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
            _parent.UseActiveSkill();
            _parent.SetInactive();   
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
            _parent.UseActiveSkill();
            _parent.SetInactive();
        }
    }
}