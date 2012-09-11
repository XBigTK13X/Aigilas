package aigilas.creatures;

import java.util.Arrays;
import java.util.List;

import spx.bridge.ActorType;
import aigilas.classes.WrathAcolyte;
import aigilas.entities.Elements;
import aigilas.gods.GodId;
import aigilas.management.SpriteType;
import aigilas.strategies.Strategy;
import aigilas.strategies.StrategyFactory;

import com.badlogic.gdx.graphics.Color;

public class Player extends AbstractCreature {
	private static List<Color> __colors = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.WHITE);

	public Player(int playerIndex) {
		super(ActorType.PLAYER, SpriteType.PLAYER_STAND, new WrathAcolyte());
		_playerIndex = playerIndex;
		_graphic.SetColor(__colors.get(_playerIndex));
		_strategy = StrategyFactory.Create(Strategy.ControlledByPlayer, this);
		_baseStats = new Stats(100f, 100f, 1f, 10f, 11f, 10f, 35f, 50f, 6.0f, 6, 1);
		_maxStats = new Stats(_baseStats);
		AssignGod(GodId.GLUTTONY.GetInstance());
		Compose(Elements.PHYSICAL);
	}
}
