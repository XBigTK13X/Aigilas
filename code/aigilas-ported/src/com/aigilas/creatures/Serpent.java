package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.aigilas.strategies.*;import com.aigilas.classes.*;import com.aigilas.management.*;import com.aigilas.entities.*;import com.aigilas.gods.*;import com.aigilas.creatures.*;import com.spx.core.*;import com.spx.devtools.*;
    public class Serpent  extends  AbstractCreature
    {
        public Serpent()
            { super(AigilasActorType.SERPENT, SpriteType.SLOTH);            Compose(Elements.EARTH);
            Strengths(StatType.HEALTH, StatType.HEALTH, StatType.HEALTH);
            _strategy = StrategyFactory.Create(Strategy.ConfusedAndDying,this);
        }
    }
