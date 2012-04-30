using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using OGUR.Strategies;
using OGUR.Classes;
using OGUR.Management;
using Microsoft.Xna.Framework;
using OGUR.Entities;
using OGUR.Gods;
using OGUR.Creatures;

namespace OGUR.Creatures
{
    public class Player : AbstractCreature
    {
        private static readonly List<Color> __colors = new List<Color>() { Color.Red,Color.Green,Color.Blue,Color.White};
        public Player(int playerIndex): base(OgurActorType.PLAYER, SpriteType.PLAYER_STAND, new WrathAcolyte())
        {
            _playerIndex = playerIndex;
            _graphic.SetColor(__colors[_playerIndex]);
            _strategy = new ControlledByPlayer(this);
            _baseStats = new Stats(100f, 100f, 1f, 10f, 11f, 10f, 35f, 50f, 6.0f, 6, 1);
            _maxStats = new Stats(_baseStats);
            AssignGod(God.Get(GodId.GLUTTONY));
            Compose(Elements.PHYSICAL);
        }
    }
    class Peon : AbstractCreature
    {
        public Peon(): base(OgurActorType.PEON)
        {
            Weaknesses(StatType.STRENGTH, StatType.HEALTH,StatType.MOVE_COOL_DOWN);
            Compose(Elements.EARTH);
        }
    }
    class Zorb : AbstractCreature
    {
        public Zorb(): base(OgurActorType.ZORB)
        {
            Compose(Elements.PHYSICAL, Elements.FIRE);
            Strengths(StatType.MANA,StatType.HEALTH);
            Weaknesses(StatType.WISDOM,StatType.DEFENSE);
            Add(SkillId.FIREBALL);
        }
    }
    class Wrath : AbstractCreature
    {
        public Wrath(): base(OgurActorType.WRATH,SpriteType.WRATH)
        {
            Compose(Elements.PHYSICAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Hand: AbstractCreature
    {
        public Hand(): base(OgurActorType.HAND,SpriteType.HAND)
        {
            Compose(Elements.PHYSICAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            _isBlocking = false;
            _strategy = new StraightLineStrategy(this);
        }
    }
    class Envy : AbstractCreature
    {
        public Envy()
            : base(OgurActorType.ENVY, SpriteType.ENVY)
        {
            Compose(Elements.WATER);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Gluttony : AbstractCreature
    {
        public Gluttony()
            : base(OgurActorType.GLUTTONY, SpriteType.GLUTTONY)
        {
            Compose(Elements.MENTAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Sloth: AbstractCreature
    {
        public Sloth()
            : base(OgurActorType.SLOTH, SpriteType.SLOTH)
        {
            Compose(Elements.EARTH);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Pride: AbstractCreature
    {
        public Pride()
            : base(OgurActorType.PRIDE, SpriteType.PRIDE)
        {
            Compose(Elements.DARK);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Greed: AbstractCreature
    {
        public Greed()
            : base(OgurActorType.GREED, SpriteType.GREED)
        {
            Compose(Elements.AIR);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Lust: AbstractCreature
    {
        public Lust()
            : base(OgurActorType.LUST, SpriteType.LUST)
        {
            Compose(Elements.FIRE);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
}
