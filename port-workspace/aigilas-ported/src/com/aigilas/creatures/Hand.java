package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.aigilas.strategies.*;import com.aigilas.classes.*;import com.aigilas.management.*;import com.aigilas.entities.*;import com.aigilas.gods.*;import com.aigilas.creatures.*;import com.spx.core.*;import com.spx.devtools.*;
public     class Hand extends  AbstractCreature
    {
        public Hand(){ super(AigilasActorType.HAND,SpriteType.HAND);            Compose(Elements.PHYSICAL);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            _isBlocking = false;
            _strategy = StrategyFactory.Create(Strategy.StraightLine, this);
        }
    }
