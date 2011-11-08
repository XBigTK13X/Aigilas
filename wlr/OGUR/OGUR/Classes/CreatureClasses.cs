using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Skills;

namespace OGUR.Classes
{
    public abstract class CreatureClass
    {
        private readonly Stats m_stats;
        protected List<KeyValuePair<int, string>> m_skillUnlocks = new List<KeyValuePair<int, string>>();

        protected CreatureClass(){}
        protected CreatureClass(Stats stats){m_stats = new Stats(stats);}
        public float GetBonus(int level,StatType stat){return m_stats.GetBonus(level,stat);}
        public IEnumerable<string> GetLevelSkills(int level){return (from skill in m_skillUnlocks where skill.Key <= level select skill.Value);}
        protected void Add(int level, string skillId){m_skillUnlocks.Add(new KeyValuePair<int, string>(level,skillId));}
    }
    class NoClass : CreatureClass
    {
        public NoClass()
            : base(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        {
        }
    }
    class SlothAcolyte:CreatureClass
    {
        public SlothAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.ACID_NOZZLE);
            Add(1, SkillId.FLOOR_SPIKES);
            Add(1, SkillId.REMOTE_MINE);
            Add(1, SkillId.VAPOR_IMPLANT);
            Add(1, SkillId.DART);
        }
    }
    class EnvyAcolyte : CreatureClass
    {
        public EnvyAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.CONFUSION);
            Add(1, SkillId.WEAK_KNEEES);
            Add(1, SkillId.VENOM_FIST);
            Add(1, SkillId.ABSORB);
            Add(1, SkillId.MUTINY);
        }
    }
    class GreedAcolyte : CreatureClass
    {
        public GreedAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.SOUL_REINFORCEMENT);
            Add(1, SkillId.HORDER);
            Add(1, SkillId.DROP_RATE_UP);
            Add(1, SkillId.THROW_ITEM);
            Add(1, SkillId.STEAL_ITEM);
        }
    }
    class WrathAcolyte : CreatureClass
    {
        public WrathAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.FLAME_HAMMER);
            Add(1, SkillId.GUSH);
            Add(1, SkillId.SOUL_CRUSH);
            Add(1, SkillId.COMBUST);
            Add(1, SkillId.HORRIFY);
        }
    }
    class GluttonyAcolyte : CreatureClass
    {
        public GluttonyAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.FORGET_SKILL);
        }
    }
    class LustAcolyte : CreatureClass
    {
        public LustAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.REGEN_ALL);
            Add(1, SkillId.SPEED_UP);
            Add(1, SkillId.ENVENOM);
            Add(1, SkillId.COLD_SHOULDER);
            Add(1, SkillId.CAVALRY);
        }
    }
    class PrideAcolyte : CreatureClass
    {
        public PrideAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.STRENGTH_UP);
            Add(1, SkillId.MANA_UP);
            Add(1, SkillId.ELECTRIFY);
            Add(1, SkillId.WALL_PUNCH);
            Add(1, SkillId.MIMIC);
            Add(1, SkillId.VALEDICTORIAN);
        }
    }
}
