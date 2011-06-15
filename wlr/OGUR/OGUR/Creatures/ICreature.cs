using System;
using System.Collections.Generic;
using OGUR.Items;
using OGUR.Strategies;
using OGUR.GameObjects;
using OGUR.Sprites;
using OGUR.Collision;

namespace OGUR.Creatures
{
    public abstract class ICreature : GameplayObject
    {
        protected IStrategy m_strategy;
        protected MentalState m_mentality = MentalState.NORMAL;
        protected List<ICreature> targets = new List<ICreature>();
        protected List<GenericItem> inventory = new List<GenericItem>();
        protected List<GenericItem> equipment = new List<GenericItem>();
        protected Stats m_stats;
        protected Stats m_maxStats;
        protected int m_playerIndex = -1;
        protected CreatureType m_creatureType;

        private SpriteType SpriteFromCreature(CreatureType type)
        {
            switch (type)
            {
                case CreatureType.PLAYER:
                    return SpriteType.PLAYER_STAND;
                default:
                    return SpriteType.CREATURE;
            }
        }

        protected void Setup(int x, int y, CreatureType type, Stats stats)
        {
            Initialize(x, y, SpriteFromCreature(type), GameObjectType.CREATURE);
            m_isBlocking = true;
            m_creatureType = type;
            m_stats = new Stats(stats);
            m_maxStats = new Stats(stats);
        }

        public override void Update()
        {
            Adjust(StatType.MOVE_COOL_DOWN, -1);
            m_strategy.Act(this);
            CheckForDamage();
        }

        public decimal Get(StatType stat)
        {
            return m_stats.Get(stat);
        }

        public decimal GetMax(StatType stat)
        {
            return (int) m_maxStats.Get(stat);
        }

        public decimal Set(StatType stat,decimal value)
        {
            return m_stats.Set(stat,value);
        }

        public decimal SetMax(StatType stat,decimal value)
        {
            return m_maxStats.Set(stat,value);
        }

        public int GetInt(StatType stat,bool getMax=false)
        {
            return (int) (getMax ? GetMax(stat) : Get(stat));
        }

        public decimal Set(StatType stat,decimal value,bool setMax=false)
        {
            return setMax ? SetMax(stat, value) : Set(stat, value);
        }

        protected void CheckForDamage()
        {
            if (Get(StatType.HEALTH) <= 0)
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

        protected decimal Adjust(StatType stat, int adjustment,bool adjustMax = false)
        {
            return Set(stat, Get(stat) + adjustment,adjustMax);
        }

        public void MoveIfPossible(int xVel, int yVel)
        {
            if ((xVel != 0 || yVel != 0) && GetInt(StatType.MOVE_COOL_DOWN) <= 0)
            {
                if (!CoordVerifier.IsBlocked(xVel + (int) GetPosition().X, yVel + (int) GetPosition().Y,this))
                {
                    Move(xVel, yVel);
                    Set(StatType.MOVE_COOL_DOWN, GetMax(StatType.MOVE_COOL_DOWN));
                }
            }
        }
    }
}