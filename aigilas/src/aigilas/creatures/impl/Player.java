package aigilas.creatures.impl;

import aigilas.classes.WrathAcolyte;
import aigilas.creatures.Stats;
import aigilas.creatures.StatsRegistry;
import aigilas.entities.Elements;
import aigilas.gods.GodId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.ActorType;

import java.util.Arrays;
import java.util.List;

public class Player extends BaseEnemy {
    private static final List<Color> __colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.WHITE);

    public Player(int playerIndex) {
        super(ActorType.Player, new WrathAcolyte());
        _playerIndex = playerIndex;
        _graphic.setColor(__colors.get(_playerIndex));
        _strategy = StrategyFactory.create(Strategy.ControlledByPlayer, this);
        _baseStats = StatsRegistry.get().baseStats(_actorType);
        _maxStats = new Stats(_baseStats);
        assignGod(GodId.Gluttony.getInstance());
        _composition.add(Elements.Physical);
    }
}
