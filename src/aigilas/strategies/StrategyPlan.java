package aigilas.strategies;

import java.util.LinkedList;

public class StrategyPlan {
    LinkedList<BaseStrategy> strategies;

    public StrategyPlan() {
        strategies = new LinkedList<BaseStrategy>();
    }

    public void add(BaseStrategy strategy) {
        strategies.push(strategy);
    }

    public BaseStrategy current() {
        return strategies.peekFirst();
    }

    public void remove(BaseStrategy strategy) {
        strategies.remove(strategy);
    }
}
