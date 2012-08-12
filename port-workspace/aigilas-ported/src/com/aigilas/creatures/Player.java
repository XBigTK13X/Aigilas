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
