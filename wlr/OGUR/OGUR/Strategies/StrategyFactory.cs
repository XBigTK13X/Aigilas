using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Creatures;

namespace OGUR.Strategies
{
    public class StrategyFactory
    {
        public static IStrategy Create(int strategy,ICreature target)
        {
            switch (strategy)
            {
                case Strategy.AttackPlayers: return new AttackStrategy(target,OgurActorType.PLAYER);
                case Strategy.ControlledByPlayer: return new ControlledByPlayer(target);
                case Strategy.Confused: return new ConfusedStrategy(target);
                case Strategy.MinionFire: return new MinionFire(target);
                case Strategy.MinionRotate: return new MinionRotate(target);
                case Strategy.MinionCloud: return new MinionCloud(target);
                case Strategy.Mutiny: return new AttackStrategy(target, OgurActorType.NONPLAYER);
                case Strategy.Flee: return new FleeStrategy(target,OgurActorType.PLAYER);
                case Strategy.AttackSelf: return new AttackSelfStrategy(target);
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
            throw new Exception("An unknown strategyType was passed into StrategyFactory.GetId(): " + strategyType);
        }
    }
}
