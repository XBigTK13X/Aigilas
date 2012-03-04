using OGUR.Classes;
using OGUR.Skills;
using OGUR.Strategies;
using SPX.Core;
using OGUR.Management;

namespace OGUR.Creatures
{
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
                _strategy = new AttackStrategy(this,OgurActorType.PLAYER);
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
        protected void Strengths(params string[] stats)
        {
            foreach (string stat in stats)
            {
                InitStat(stat, Get(stat) * 2);
            }
        }
        protected void Weaknesses(params string[] stats)
        {
            foreach (string stat in stats)
            {
                InitStat(stat, Get(stat) * .5f);
            }
        }
        protected void Compose(params int[] elems)
        {
            _composition.AddRange(elems);
        }
    }
}
