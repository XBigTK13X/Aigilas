package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.strategies.*;import com.aigilas.entities.*;import com.spx.entities.*;import com.spx.core.*;
public     class DartTrap  extends  Minion
    {
        public DartTrap()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionFire, this);
            Add(SkillId.DART);
            _composition.add(Elements.DARK);
        }
    }

