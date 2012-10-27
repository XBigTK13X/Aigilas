package aigilas.creatures.impl;

import aigilas.classes.WrathAcolyte;
import aigilas.creatures.Stats;
import aigilas.entities.Elements;
import aigilas.gods.GodId;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;
import com.badlogic.gdx.graphics.Color;
import sps.bridge.ActorType;
import sps.core.Settings;

import java.util.Arrays;
import java.util.List;

public class Player extends BaseEnemy {
    private static final List<Color> __colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.WHITE);

    public Player(int playerIndex) {
        super(ActorType.Player, new WrathAcolyte());
        _playerIndex = playerIndex;
        _graphic.setColor(__colors.get(_playerIndex));
        _strategy = StrategyFactory.create(Strategy.ControlledByPlayer, this);
        //TODO Move into config
        _baseStats = new Stats(5000, 500, 100, 1, 90, 10, 35, 50, 6, Settings.get().defaultSpeed * 3, Settings.get().defaultRegen);
        _maxStats = new Stats(_baseStats);
        assignGod(GodId.Gluttony.getInstance());
        _composition.add(Elements.Physical);
    }
}
