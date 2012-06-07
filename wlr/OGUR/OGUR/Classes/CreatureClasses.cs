using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;
using OGUR.Skills;

namespace OGUR.Classes
{
    [Serializable]
    public class CreatureClass
    {
        private readonly Stats _stats;
        protected List<KeyValuePair<int, string>> _skillUnlocks = new List<KeyValuePair<int, string>>();
        public static readonly CreatureClass NULL = new NoClass();

        protected CreatureClass(){}
        protected CreatureClass(Stats stats){_stats = new Stats(stats);}
        public float GetBonus(int level,string stat){return _stats.GetBonus(level,stat);}
        public IEnumerable<string> GetLevelSkills(int level){return (from skill in _skillUnlocks where skill.Key <= level select skill.Value);}
        protected void Add(int level, string skillId){_skillUnlocks.Add(new KeyValuePair<int, string>(level,skillId));}
    }
    [Serializable]
    class NoClass : CreatureClass
    {
        public NoClass()
            : base(new Stats(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))
        {
        }
    }
    [Serializable]
    class SlothAcolyte:CreatureClass
    {
        public SlothAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.ACID_NOZZLE);
            Add(2, SkillId.FLOOR_SPIKES);
            Add(3, SkillId.REMOTE_MINE);
            Add(4, SkillId.VAPOR_IMPLANT);
            Add(5, SkillId.DART_TRAP);
        }
    }
    [Serializable]
    class EnvyAcolyte : CreatureClass
    {
        public EnvyAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.CONFUSION);
            Add(2, SkillId.WEAK_KNEEES);
            Add(3, SkillId.VENOM_FIST);
            Add(4, SkillId.ABSORB);
            Add(5, SkillId.MUTINY);
        }
    }
    [Serializable]
    class GreedAcolyte : CreatureClass
    {      public GreedAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.SOUL_REINFORCEMENT);
            Add(2, SkillId.HORDER);
            Add(3, SkillId.SPAWN_ITEM);
            Add(4, SkillId.THROW_ITEM);
            Add(5, SkillId.STEAL_ITEM);
        }
    }
    [Serializable]
    class WrathAcolyte : CreatureClass
    {
        public WrathAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.STRENGTH_UP);
            Add(1, SkillId.MANA_UP);
            Add(1, SkillId.FLAME_HAMMER);
            Add(2, SkillId.GUSH);
            Add(3, SkillId.SOUL_CRUSH);
            Add(4, SkillId.COMBUST);
            Add(5, SkillId.HORRIFY);
        }
    }
    [Serializable]
    class GluttonyAcolyte : CreatureClass
    {
        public GluttonyAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.FORGET_SKILL);
        }
    }
    [Serializable]
    class LustAcolyte : CreatureClass
    {
        public LustAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.REGEN_ALL);
            Add(2, SkillId.SPEED_UP);
            Add(3, SkillId.ENVENOM);
            Add(4, SkillId.COLD_SHOULDER);
            Add(5, SkillId.CAVALRY);
        }
    }
    [Serializable]
    class PrideAcolyte : CreatureClass
    {
        public PrideAcolyte(): base(new Stats(5, 10, 1, 5, 0, 0, 1, 0, 0, 0))
        {
            Add(1, SkillId.STRENGTH_UP);
            Add(2, SkillId.MANA_UP);
            Add(3, SkillId.ELECTRIFY);
            Add(4, SkillId.WALL_PUNCH);
            Add(5, SkillId.MIMIC);
            Add(6, SkillId.VALEDICTORIAN);
        }
    }
}
