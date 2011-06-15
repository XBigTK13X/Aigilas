using System.Collections.Generic;

namespace OGUR.Creatures
{
    class Stats
    {
        public const decimal DefaultMoveSpeed = 33;
        public const decimal DefaultCoolDown = 6;
        private readonly Dictionary<StatType, decimal> m_stats = new Dictionary<StatType, decimal>();
        public Stats(Stats target)
        {
            m_stats = new Dictionary<StatType, decimal>(target.m_stats);
        }
        public Stats
            (   
                decimal health,
                decimal mana, 
                decimal strength, 
                decimal wisdom, 
                decimal defense, 
                decimal luck, 
                decimal age, 
                decimal moveSpeed = DefaultMoveSpeed, 
                decimal moveCoolDown = DefaultCoolDown
            )
        {
            m_stats.Add(StatType.AGE,age);
            m_stats.Add(StatType.DEFENSE, defense);
            m_stats.Add(StatType.HEALTH, health);
            m_stats.Add(StatType.LUCK, luck);
            m_stats.Add(StatType.MANA, mana);
            m_stats.Add(StatType.MOVE_COOL_DOWN, moveCoolDown);
            m_stats.Add(StatType.MOVE_SPEED, moveSpeed);
            m_stats.Add(StatType.STRENGTH, strength);
            m_stats.Add(StatType.WISDOM, wisdom);
        }
        public decimal Get(StatType stat)
        {
            return m_stats[stat];
        }

        public decimal Set(StatType stat, decimal value)
        {
            return m_stats[stat] = value;
        }
    }
}
