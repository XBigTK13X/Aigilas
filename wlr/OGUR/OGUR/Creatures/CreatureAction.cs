using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace OGUR.Creatures
{
    public enum OAction
    {
        Movement,
        Attacking,
        SkillCycle,
        Regeneration,
        WontHitNonTargets,
        ReceiveHealing,
        SkillUsage
    }
}
