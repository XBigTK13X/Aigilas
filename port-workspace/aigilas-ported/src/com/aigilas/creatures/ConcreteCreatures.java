package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.aigilas.strategies.*;import com.aigilas.classes.*;import com.aigilas.management.*;import com.aigilas.entities.*;import com.aigilas.gods.*;import com.aigilas.creatures.*;import com.spx.core.*;import com.spx.devtools.*;
    public class Player  extends  AbstractCreature
    {
        private static List<Color> __colors = Arrays.asList(Color.Red,Color.Green,Color.Blue,Color.White);
        public Player(int playerIndex){ super(AigilasActorType.PLAYER, SpriteType.PLAYER_STAND, new WrathAcolyte());            _playerIndex = playerIndex;
            _graphic.SetColor(__colors.get(_playerIndex));
            _strategy = StrategyFactory.Create(Strategy.ControlledByPlayer, this);
            _baseStats = new Stats(100f, 100f, 1f, 10f, 11f, 10f, 35f, 50f, 6.0f, 6, 1);
            _maxStats = new Stats(_baseStats);
            AssignGod(God.Get(GodId.GLUTTONY));
            Compose(Elements.PHYSICAL);
        }
    }
    class Peon  extends  AbstractCreature
    {
        public Peon(){ super(AigilasActorType.PEON);            Weaknesses(StatType.STRENGTH, StatType.HEALTH,StatType.MOVE_COOL_DOWN);
            Compose(Elements.EARTH);
        }
    }
    class Zorb  extends  AbstractCreature
    {
        public Zorb(){ super(AigilasActorType.ZORB);            Compose(Elements.PHYSICAL, Elements.FIRE);
            Strengths(StatType.MANA,StatType.HEALTH);
            Weaknesses(StatType.WISDOM,StatType.DEFENSE);
            Add(SkillId.FIREBALL);
        }
    }
    class Wrath  extends  AbstractCreature
    {
        public Wrath(){ super(AigilasActorType.WRATH,SpriteType.WRATH);            Compose(Elements.PHYSICAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.DISMEMBERMENT);
        }
    }
    class Hand extends  AbstractCreature
    {
        public Hand(){ super(AigilasActorType.HAND,SpriteType.HAND);            Compose(Elements.PHYSICAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            _isBlocking = false;
            _strategy = StrategyFactory.Create(Strategy.StraightLine, this);
        }
    }
    class Envy  extends  AbstractCreature
    {
        public Envy()
            { super(AigilasActorType.ENVY, SpriteType.ENVY);            Compose(Elements.WATER);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.HYPOTHERMIA);
        }
    }
    class Gluttony  extends  AbstractCreature
    {
        public Gluttony()
            { super(AigilasActorType.GLUTTONY, SpriteType.GLUTTONY);            Compose(Elements.MENTAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.PLAGUE);
        }
    }
    class Sloth extends  AbstractCreature
    {
        public Sloth()
            { super(AigilasActorType.SLOTH, SpriteType.SLOTH);            Compose(Elements.EARTH);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.SERPENT_SUPPER);
        }
    }
    public class Serpent  extends  AbstractCreature
    {
        public Serpent()
            { super(AigilasActorType.SERPENT, SpriteType.SLOTH);            Compose(Elements.EARTH);
            Strengths(StatType.HEALTH, StatType.HEALTH, StatType.HEALTH);
            _strategy = StrategyFactory.Create(Strategy.ConfusedAndDying,this);
        }
    }
    class Pride extends  AbstractCreature
    {
        public Pride()
            { super(AigilasActorType.PRIDE, SpriteType.PRIDE);            Compose(Elements.DARK);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BREAKING_WHEEL);
        }
    }
    class Greed extends  AbstractCreature
    {
        public Greed()
            { super(AigilasActorType.GREED, SpriteType.GREED);            Compose(Elements.AIR);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BOIL);
        }
    }
    class Lust extends  AbstractCreature
    {
        public Lust()
            { super(AigilasActorType.LUST, SpriteType.LUST);            Compose(Elements.FIRE);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BRIMSTONE);
        }
    }
    public class BreakingWheel  extends  AbstractCreature
    {
        public BreakingWheel()
            { super(AigilasActorType.BREAKING_WHEEL);            _strategy = StrategyFactory.Create(Strategy.StraightLineRotate,this);
            _composition.add(Elements.DARK);
            Strengths(StatType.MOVE_COOL_DOWN, StatType.MOVE_COOL_DOWN);
        }
    }
