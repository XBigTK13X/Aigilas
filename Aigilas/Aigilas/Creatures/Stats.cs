using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using SPX.Util;
using SPX.Core;

namespace Aigilas.Creatures
{
    public class Stats
    {
        public static readonly float DefaultMoveSpeed = GameManager.SpriteHeight;
        public const float DefaultCoolDown = 10;
        public const float DefaultRegenRate = 1;

        private readonly Dictionary<string, float> _stats = new Dictionary<string, float>();
        private readonly List<StatBuff> _buffs = new List<StatBuff>(); 

        public Stats(Stats target)
        {
            _stats = new Dictionary<string, float>(target._stats);
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
                float moveCoolDown = DefaultCoolDown,
                float regenRate = DefaultRegenRate
            )
        {
            Setup(new List<float>{health, mana, strength, wisdom, defense,luck, age,weightInLbs, heightInFeet, moveCoolDown,0,regenRate});
        }

        private void Setup(List<float> stats)
        {
            for (var ii = 0; ii < stats.Count; ii++)
            {
                _stats.Add(StatType.Values[ii], stats[ii]);
            }
        }
        private float statSum = 0;
        public float Get(string stat)
        {
            if (_buffs != null)
            {
                if (!_buffs.Contains(null))
                {
                    statSum = 0;
                    for (int ii = 0; ii < _buffs.Count(); ii++)
                    {
                        if (_buffs[ii].Stat == stat)
                        {
                            statSum += _buffs[ii].Amount;
                        }
                    }
                    return GetRaw(stat) + statSum;
                }
            }
            return GetRaw(stat);
        }

        public float GetRaw(string stat)
        {
            return _stats[stat];
        }

        public float Set(string stat, float value)
        {
            return _stats[stat] = value;
        }

        public void AddBuff(StatBuff buff)
        {
            if(_buffs.Contains(buff))
            {
                _buffs.Remove(buff);
                return;
            }
            _buffs.Add(buff);
        }

        private readonly List<float> deltas = new List<float>();
        public List<float> GetDeltas(Stats stats)
        {
            deltas.Clear();
            foreach (var stat in StatType.Values)
            {
                deltas.Add(_stats[stat] - stats._stats[stat]);
            }
            return deltas;
        }

        public Stats GetLevelBonuses(int level)
        {
            var result = new Stats(this);
            foreach(var stat in result._stats.Keys)
            {
                result._stats[stat] += result._stats[stat]*level;
            }
            return result;
        }

        public float GetBonus(int level, string stat)
        {
            return _stats[stat]*level;
        }

        public float GetSum()
        {
            return _stats.Keys.Where(o=>o!=StatType.HEALTH&&o!=StatType.MOVE_COOL_DOWN&&o!=StatType.REGEN).Sum(stat => _stats[stat]);
        }

        float hash = 0;
        public override int GetHashCode()
        {
            hash = 0;
            foreach(var key in _stats.Keys)
            {
                hash += _stats[key];
            }
            return hash.GetHashCode();
        }
    }
}
