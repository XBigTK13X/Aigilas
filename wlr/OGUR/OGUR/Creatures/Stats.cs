using System;
using System.Collections.Generic;
using System.Linq;

namespace OGUR.Creatures
{
    public class Stats
    {
        public const double DefaultMoveSpeed = 33;
        public const double DefaultCoolDown = 6;
        private readonly Dictionary<StatType, decimal> m_stats = new Dictionary<StatType, decimal>();

        public Stats(Stats target)
        {
            m_stats = new Dictionary<StatType, decimal>(target.m_stats);
        }
        public Stats
            (
                double health,
                double mana,
                double strength,
                double wisdom,
                double defense,
                double luck,
                double age,
                double weightInLbs,
                double heightInFeet,
                double moveSpeed = (double)DefaultMoveSpeed,
                double moveCoolDown = (double)DefaultCoolDown
            )
        {
            Setup(new List<decimal>(){(decimal) health, (decimal) mana, (decimal) strength, (decimal) wisdom, (decimal) defense,
                             (decimal) luck, (decimal) age,
                             (decimal) weightInLbs, (decimal) heightInFeet, (decimal) moveSpeed, (decimal) moveCoolDown});
        }

        private void Setup(List<decimal> stats )
        {
            for (int ii = 0; ii < stats.Count; ii++)
            {
                m_stats.Add((StatType)Enum.GetValues(typeof(StatType)).GetValue(ii), stats[ii]);
            }
        }
        public decimal Get(StatType stat)
        {
            return m_stats[stat];
        }

        public decimal Set(StatType stat, decimal value)
        {
            return m_stats[stat] = value;
        }

        public override int GetHashCode()
        {
            string hash = m_stats.Aggregate("", (current, pair) => current + m_stats.ToString());
            return hash.GetHashCode();
        }
    }
}
