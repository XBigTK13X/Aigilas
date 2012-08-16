package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.strategies.*;import com.aigilas.entities.*;import com.spx.entities.*;import com.spx.core.*;
public     class AcidNozzle  extends  Minion
    {
        public AcidNozzle()
            { super(AigilasActorType.MINION,50f);            Add(SkillId.ACID_DRIP);
            _composition.add(Elements.EARTH);
            _strategy = StrategyFactory.Create(Strategy.MinionRotate,this);
        }
    }

