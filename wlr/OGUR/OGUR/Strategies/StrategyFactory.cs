using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public class Strategy
    {
        // max = 15
        public const int Null = -1;
        public const int Attack = 0;
        public const int ControlledByPlayer = 1;
        public const int Confused = 2;
        public const int ConfusedAndDying = 15;
        public const int MinionRotate = 3;
        public const int MinionFire = 4;
        public const int MinionExplode = 5;
        public const int MinionCloud = 6;
        public const int Mutiny = 7;
        public const int Flee = 8;
        public const int AttackSelf = 9;
        public const int MinionOneUse = 10;
        public const int StraightLineRotate = 11;
        public const int StraightLine = 12;
        public const int TestBot = 13;
    }
    public class StrategyFactory
    {
        public static IStrategy Create(int strategy,ICreature target,params int[] ogurActorTypes)
        {
            switch (strategy)
            {
                case Strategy.Attack: return new AttackStrategy(target,OgurActorType.PLAYER);
                case Strategy.AttackSelf: return new AttackSelfStrategy(target);
                case Strategy.Confused: return new ConfusedStrategy(target);
                case Strategy.ConfusedAndDying: return new ConfusedAndDyingStrategy(target);
                case Strategy.ControlledByPlayer: return new ControlledByPlayer(target);               
                case Strategy.Flee: return new FleeStrategy(target,OgurActorType.PLAYER);
                case Strategy.MinionCloud: return new MinionCloudStrategy(target);
                case Strategy.MinionExplode: return new MinionOneUseStrategy(target);
                case Strategy.MinionFire: return new MinionFireStrategy(target);
                case Strategy.MinionOneUse: return new MinionOneUseStrategy(target);
                case Strategy.MinionRotate: return new MinionRotateStrategy(target);             
                case Strategy.Mutiny: return new AttackStrategy(target, OgurActorType.NONPLAYER);
                case Strategy.Null: return new NullStrategy(target);
                case Strategy.StraightLineRotate: return new StraightLineRotateStrategy(target);
                case Strategy.StraightLine: return new StraightLineStrategy(target);
                case Strategy.TestBot: return new TestBotStrategy(target);                
                default: throw new Exception("An undefined strategy was passed into the strategy factory: " + strategy);
            }
        }
    }
}
