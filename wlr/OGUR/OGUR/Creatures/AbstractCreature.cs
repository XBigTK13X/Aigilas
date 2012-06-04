using OGUR.Classes;
using OGUR.Skills;
using OGUR.Strategies;
using SPX.Core;
using OGUR.Management;
using System;

namespace OGUR.Creatures
{
    [Serializable]
    public class AbstractCreature:ICreature
    {
        public AbstractCreature(int actorType,int spriteType = SpriteType.CREATURE,CreatureClass cClass = null) 
        {
            SetClass(cClass);
            _actorType = actorType;
            _baseStats = new Stats(3, 1, 1, 1, 1, 1, 1, 1, 1);
            _maxStats = new Stats(_baseStats);
        }
        public void Setup(Point2 position)
        {
            Setup(position, _actorType, _baseStats, _class);
            if (_strategy == null)
            {
                _strategy = StrategyFactory.Create(Strategy.Attack,this,OgurActorType.PLAYER);
            }
        }
        protected void Add(string skillId)
        {
            if (_skills == null)
            {
                _skills = new SkillPool(this);
            }
            _skills.Add(skillId);
        }
        float multiplier = 0f;        
        protected void Strengths(params string[] stats)
        {
            foreach (string stat in stats)
            {
                multiplier = (stat == StatType.MOVE_COOL_DOWN) ? .5f : 2;
                InitStat(stat, Get(stat) * multiplier);
            }
        }
        protected void Weaknesses(params string[] stats)
        {
            foreach (string stat in stats)
            {
                multiplier = (stat == StatType.MOVE_COOL_DOWN) ? 2 : .5f;
                InitStat(stat, Get(stat) * multiplier);
            }
        }
        protected void Compose(params int[] elems)
        {
            _composition.AddRange(elems);
        }
    }
}
