using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public abstract class Strategy
    {
        // max = 14
        public const int Null = -1;
        public const int AttackPlayers = 0;
        public const int ControlledByPlayer = 1;
        public const int Confused = 2;
        public const int MinionRotate = 3;
        public const int MinionFire = 4;
        public const int MinionExplode = 5;
        public const int MinionCloud = 6;
        public const int Mutiny = 7;
        public const int Flee = 8;
        public const int AttackSelf = 9;
        public const int MinionOneUse = 10;
        public const int StraightLineRotate = 11;
        public const int StraightLineStrategy = 12;
        public const int TestBotStrategy = 13;
    }
    public class StrategyFactory
    {
        public static IStrategy Create(int strategy,ICreature target)
        {
            switch (strategy)
            {
                case Strategy.AttackPlayers: return new AttackStrategy(target,OgurActorType.PLAYER);
                case Strategy.AttackSelf: return new AttackSelfStrategy(target);
                case Strategy.Confused: return new ConfusedStrategy(target);
                case Strategy.ControlledByPlayer: return new ControlledByPlayer(target);               
                case Strategy.Flee: return new FleeStrategy(target,OgurActorType.PLAYER);
                case Strategy.MinionCloud: return new MinionCloud(target);
                case Strategy.MinionExplode: return new MinionOneUse(target);
                case Strategy.MinionFire: return new MinionFire(target);
                case Strategy.MinionRotate: return new MinionRotate(target);             
                case Strategy.Mutiny: return new AttackStrategy(target, OgurActorType.NONPLAYER);
                case Strategy.Null: return new NullStrategy(target);
                case Strategy.StraightLineRotate: return new StraightLineRotate(target);
                case Strategy.StraightLineStrategy: return new StraightLineStrategy(target);
                case Strategy.TestBotStrategy: return new TestBotStrategy(target);                
                default: throw new Exception("An undefined strategy was passed into the strategy factory: " + strategy);
            }
        }

        public static int GetId(Type strategyType)
        {
            if (strategyType == typeof(ControlledByPlayer))
            {
                return Strategy.ControlledByPlayer;
            }
            if (strategyType == typeof(AttackStrategy))
            {
                return Strategy.AttackPlayers;
            }
            if (strategyType == typeof(ConfusedStrategy))
            {
                return Strategy.Confused;
            }
            if (strategyType == typeof(MinionRotate))
            {
                return Strategy.MinionRotate;
            }
            if (strategyType == typeof(MinionFire))
            {
                return Strategy.MinionFire;
            }
            if (strategyType == typeof(MinionFire))
            {
                return Strategy.MinionExplode;
            }
            if (strategyType == typeof(MinionFire))
            {
                return Strategy.MinionExplode;
            }
            if (strategyType == typeof(FleeStrategy))
            {
                return Strategy.Flee;
            }
            if (strategyType == typeof(AttackSelfStrategy))
            {
                return Strategy.Flee;
            }
            if (strategyType == typeof(MinionOneUse))
            {
                return Strategy.MinionOneUse;
            }
            if (strategyType == typeof(StraightLineRotate))
            {
                return Strategy.StraightLineRotate;
            }
            if (strategyType == typeof(StraightLineStrategy))
            {
                return Strategy.StraightLineStrategy;
            }
            if (strategyType == typeof(TestBotStrategy))
            {
                return Strategy.TestBotStrategy;
            }
            if (strategyType == typeof(NullStrategy))
            {
                return Strategy.Null;
            }
            throw new Exception("An unknown strategyType was passed into StrategyFactory.GetId(): " + strategyType);
        }
    }
}
