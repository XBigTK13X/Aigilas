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
                case Strategy.AttackPlayers: return new AttackPlayers(target);
                case Strategy.ControlledByPlayer: return new ControlledByPlayer(target);
                case Strategy.Confused: return new ConfusedStrategy(target);
                default: throw new Exception("An undefined strategy was passed into the strategy factory: " + strategy);
            }
        }

        public static int GetId(Type strategyType)
        {
            if (strategyType == typeof(ControlledByPlayer))
            {
                return Strategy.ControlledByPlayer;
            }
            if (strategyType == typeof(AttackPlayers))
            {
                return Strategy.AttackPlayers;
            }
            if (strategyType == typeof(ConfusedStrategy))
            {
                return Strategy.Confused;
            }
            throw new Exception("An unknown strategyType was passed into StrategyFactory.GetId(): " + strategyType);
        }
    }
}
