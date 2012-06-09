using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Agilas.Skills;
using Agilas.Strategies;
using Agilas.Classes;
using Agilas.Management;
using Microsoft.Xna.Framework;
using Agilas.Entities;
using Agilas.Gods;
using Agilas.Creatures;

namespace Agilas.Creatures
{
    public class Player : AbstractCreature
    {
        private static readonly List<Color> __colors = new List<Color>() { Color.Red,Color.Green,Color.Blue,Color.White};
        public Player(int playerIndex): base(AgilasActorType.PLAYER, SpriteType.PLAYER_STAND, new WrathAcolyte())
        {
            _playerIndex = playerIndex;
            _graphic.SetColor(__colors[_playerIndex]);
            _strategy = StrategyFactory.Create(Strategy.ControlledByPlayer, this);
            _baseStats = new Stats(100f, 100f, 1f, 10f, 11f, 10f, 35f, 50f, 6.0f, 6, 1);
            _maxStats = new Stats(_baseStats);
            AssignGod(God.Get(GodId.GLUTTONY));
            Compose(Elements.PHYSICAL);
        }
    }
    class Peon : AbstractCreature
    {
        public Peon(): base(AgilasActorType.PEON)
        {
            Weaknesses(StatType.STRENGTH, StatType.HEALTH,StatType.MOVE_COOL_DOWN);
            Compose(Elements.EARTH);
        }
    }
    class Zorb : AbstractCreature
    {
        public Zorb(): base(AgilasActorType.ZORB)
        {
            Compose(Elements.PHYSICAL, Elements.FIRE);
            Strengths(StatType.MANA,StatType.HEALTH);
            Weaknesses(StatType.WISDOM,StatType.DEFENSE);
            Add(SkillId.FIREBALL);
        }
    }
    class Wrath : AbstractCreature
    {
        public Wrath(): base(AgilasActorType.WRATH,SpriteType.WRATH)
        {
            Compose(Elements.PHYSICAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Hand: AbstractCreature
    {
        public Hand(): base(AgilasActorType.HAND,SpriteType.HAND)
        {
            Compose(Elements.PHYSICAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            _isBlocking = false;
            _strategy = StrategyFactory.Create(Strategy.StraightLine, this);
        }
    }
    class Envy : AbstractCreature
    {
        public Envy()
            : base(AgilasActorType.ENVY, SpriteType.ENVY)
        {
            Compose(Elements.WATER);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.HYPOTHERMIA);
        }
    }
    class Gluttony : AbstractCreature
    {
        public Gluttony()
            : base(AgilasActorType.GLUTTONY, SpriteType.GLUTTONY)
        {
            Compose(Elements.MENTAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.PLAGUE);
        }
    }
    class Sloth: AbstractCreature
    {
        public Sloth()
            : base(AgilasActorType.SLOTH, SpriteType.SLOTH)
        {
            Compose(Elements.EARTH);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.SERPENT_SUPPER);
        }
    }
    public class Serpent : AbstractCreature
    {
        public Serpent()
            : base(AgilasActorType.SERPENT, SpriteType.SLOTH)
        {
            Compose(Elements.EARTH);
            Strengths(StatType.HEALTH, StatType.HEALTH, StatType.HEALTH);
            _strategy = StrategyFactory.Create(Strategy.ConfusedAndDying,this);
        }
    }
    class Pride: AbstractCreature
    {
        public Pride()
            : base(AgilasActorType.PRIDE, SpriteType.PRIDE)
        {
            Compose(Elements.DARK);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BREAKING_WHEEL);
        }
    }
    class Greed: AbstractCreature
    {
        public Greed()
            : base(AgilasActorType.GREED, SpriteType.GREED)
        {
            Compose(Elements.AIR);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BOIL);
        }
    }
    class Lust: AbstractCreature
    {
        public Lust()
            : base(AgilasActorType.LUST, SpriteType.LUST)
        {
            Compose(Elements.FIRE);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BRIMSTONE);
        }
    }
    public class BreakingWheel : AbstractCreature
    {
        public BreakingWheel()
            : base(AgilasActorType.BREAKING_WHEEL)
        {
            _strategy = StrategyFactory.Create(Strategy.StraightLineRotate,this);
            _composition.Add(Elements.DARK);
            Strengths(StatType.MOVE_COOL_DOWN, StatType.MOVE_COOL_DOWN);
        }
    }
}
