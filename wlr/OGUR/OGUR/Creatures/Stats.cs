using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace OGUR.Creatures
{
    public class Stats
    {
        public const float DefaultMoveSpeed = 33;
        public const float DefaultCoolDown = 6;
        private readonly Dictionary<StatType, float> m_stats = new Dictionary<StatType, float>();

        public Stats(Stats target)
        {
            m_stats = new Dictionary<StatType, float>(target.m_stats);
        }
        public Stats
            (
                float health,
                float mana,
                float strength,
                float wisdom,
                float defense,
                float luck,
                float age,
                float weightInLbs,
                float heightInFeet,
                float moveSpeed = (float)DefaultMoveSpeed,
                float moveCoolDown = (float)DefaultCoolDown
            )
        {
            Setup(new List<float>(){(float) health, (float) mana, (float) strength, (float) wisdom, (float) defense,
                             (float) luck, (float) age,
                             (float) weightInLbs, (float) heightInFeet, (float) moveSpeed, (float) moveCoolDown,0});
        }

        private void Setup(List<float> stats )
        {
            for (int ii = 0; ii < stats.Count; ii++)
            {
                m_stats.Add((StatType)Enum.GetValues(typeof(StatType)).GetValue(ii), stats[ii]);
            }
        }
        public float Get(StatType stat)
        {
            return m_stats[stat];
        }

        public float Set(StatType stat, float value)
        {
            return m_stats[stat] = value;
        }

        public override int GetHashCode()
        {
            string hash = m_stats.Aggregate("", (current, pair) => current + m_stats.ToString());
            return hash.GetHashCode();
        }

        public IEnumerable GetDeltas(Stats stats)
        {
            return m_stats.Select((t, ii) => m_stats[(StatType) ii] - stats.m_stats[(StatType) ii]).ToList();
        }

        public Stats GetLevelBonuses(int level)
        {
            var result = new Stats(this);
            foreach(var stat in result.m_stats.Keys)
            {
                result.m_stats[stat] += result.m_stats[stat]*level;
            }
            return result;
        }

        public float GetBonus(int level, StatType stat)
        {
            return m_stats[stat]*level;
        }

        public float GetSum()
        {
            return m_stats.Keys.Where(o=>o!=StatType.HEALTH&&o!=StatType.MOVE_COOL_DOWN).Sum(stat => m_stats[stat]);
        }
    }
}
