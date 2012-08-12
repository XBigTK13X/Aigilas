package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.aigilas.strategies.*;import com.aigilas.classes.*;import com.aigilas.management.*;import com.aigilas.entities.*;import com.aigilas.gods.*;import com.aigilas.creatures.*;import com.spx.core.*;import com.spx.devtools.*;
    public class BreakingWheel  extends  AbstractCreature
    {
        public BreakingWheel()
            { super(AigilasActorType.BREAKING_WHEEL);            _strategy = StrategyFactory.Create(Strategy.StraightLineRotate,this);
            _composition.add(Elements.DARK);
            Strengths(StatType.MOVE_COOL_DOWN, StatType.MOVE_COOL_DOWN);
        }
    }
