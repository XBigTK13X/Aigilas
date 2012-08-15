package com.aigilas.strategies.impl;
import com.xna.wrapper.*;
import java.util.*;
import com.aigilas.creatures.*;
import com.spx.core.*;
import com.aigilas.strategies.*;

    public class ConfusedAndDyingStrategy extends  ConfusedStrategy
    {
        public ConfusedAndDyingStrategy(ICreature parent)
            
{
 super(parent);

        }
@Override
        public  void Act()
        {
            super.Act();
            _parent.ApplyDamage(1.1f,null,false);
        }
    }
