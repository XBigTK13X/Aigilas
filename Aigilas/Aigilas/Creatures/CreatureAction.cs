using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Aigilas.Creatures
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
