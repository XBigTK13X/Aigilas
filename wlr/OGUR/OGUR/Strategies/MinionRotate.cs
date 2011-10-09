using System;
using OGUR.Collision;
using OGUR.Creatures;
using OGUR.Dungeons;
using OGUR.Path;

namespace OGUR.Strategies
{
    public class MinionRotate : IStrategy
    {
        public MinionRotate(ICreature parent) : base(parent)
        {
            parent.SetSkillVector(new Point2(1,0));
        }

        public override void Act(ICreature target)
        {
            target.SetSkillVector(target.GetSkillVector().RotateClockwise());
            target.UseActiveSkill();
            target.ApplyDamage(1, null, false);
            target.Set(StatType.MOVE_COOL_DOWN, target.GetMax(StatType.MOVE_COOL_DOWN));
        }
    }
}