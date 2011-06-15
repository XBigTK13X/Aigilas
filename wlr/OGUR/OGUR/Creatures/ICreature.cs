using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Strategies;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Collision;

namespace OGUR.Creatures
{
    abstract class ICreature:GameplayObject
    {

        protected IStrategy m_strategy;
        protected MentalState m_mentality = MentalState.NORMAL;
        protected List<ICreature> targets = new List<ICreature>();
        protected List<IItem> inventory = new List<IItem>();
        protected List<IItem> equipment = new List<IItem>();
        protected List<decimal> m_stats = new List<decimal>();
        protected List<decimal> m_maxStats = new List<decimal>();
        protected int m_playerIndex = -1;
        protected CreatureType m_creatureType;
        static protected int COOLDOWN = 6;

        protected void Setup(int x, int y,CreatureType type,SpriteType sprite)
        {
            Initialize(x, y,sprite,GameObjectType.CREATURE);
            m_isBlocking = true;
            m_creatureType = type;
            foreach(Stat stat in Enum.GetValues(typeof(Stat)).Cast<Stat>())
            {
                m_stats.Add(0);
                m_maxStats.Add(1);
            }
        }

        public override void Update()
        {
            Adjust(Stat.MOVE_COOL_DOWN, -1);
            m_strategy.Act(this);
            CheckForDamage();
        }

        public decimal GetStat(Stat stat)
        {
            return m_stats[(int)stat];
        }

        public int GetInt(Stat stat)
        {
            return (int)GetStat(stat);
        }

        protected void CheckForDamage()
        {
            if (m_stats[(int)Stat.HEALTH] <= 0)
            {
                m_isActive = false;
            }
        }

        public int GetPlayerIndex()
        {
            return m_playerIndex;
        }

        public CreatureType GetCreatureType()
        {
            return m_creatureType;
        }

        protected decimal Adjust(Stat stat, int adjustment)
        {
            m_stats[(int)stat] = m_stats[(int)stat] + adjustment;
            return m_stats[(int)stat];
        }

        protected void InitStats(List<decimal> stats)
        {
            m_maxStats = new List<decimal>();
            m_stats = new List<decimal>();
            foreach (decimal item in stats)
            {
                m_maxStats.Add(item);
                m_stats.Add(item);
            }
        }
        
        public void MoveIfPossible(int xVel, int yVel)
        {
            if ((xVel != 0 || yVel != 0) && GetInt(Stat.MOVE_COOL_DOWN) <= 0)
            {
                if (!CoordVerifier.IsBlocked(xVel + (int)GetPosition().X, yVel + (int)GetPosition().Y))
                {
                    Move(xVel, yVel);
                    m_stats[(int)Stat.MOVE_COOL_DOWN] = m_maxStats[(int)Stat.MOVE_COOL_DOWN];
                }
            }
        }
    }
}
