package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.strategies.*;import com.aigilas.entities.*;import com.spx.entities.*;import com.spx.core.*;
public     class VaporCloud  extends  Minion
    {
        private ICreature _host = null;
        public VaporCloud()
            { super(AigilasActorType.MINION);            _strategy = StrategyFactory.Create(Strategy.MinionCloud, this);
            Add(SkillId.VAPOR_CLOUD);
            _composition.add(Elements.WATER);
        }
@Override        public  void Update()
        {
            super.Update();
            if (_host == null)
            {
                for (IActor creature:EntityManager.GetActorsAt(_location))
                {
                    if (creature != this)
                    {
                        _host = (ICreature)creature;
                    }
                }
                if (_host == null)
                {
                    SetInactive();
                }
            }
            if (_host != null)
            {
                SetLocation(_host.GetLocation());
            }
        }
    }
