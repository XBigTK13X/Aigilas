package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.strategies.*;import com.aigilas.entities.*;import com.spx.entities.*;import com.spx.core.*;
public     class Explosion  extends  Minion
    {
        public Explosion()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionOneUse, this);
            Add(SkillId.EXPLODE);
            _composition.add(Elements.FIRE);
        }
    }

