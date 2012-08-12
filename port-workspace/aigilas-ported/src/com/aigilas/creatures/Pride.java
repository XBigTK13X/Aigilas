package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.aigilas.strategies.*;import com.aigilas.classes.*;import com.aigilas.management.*;import com.aigilas.entities.*;import com.aigilas.gods.*;import com.aigilas.creatures.*;import com.spx.core.*;import com.spx.devtools.*;
public     class Pride extends  AbstractCreature
    {
        public Pride()
            { super(AigilasActorType.PRIDE, SpriteType.PRIDE);            Compose(Elements.DARK);
            Strengths(StatType.STRENGTH, StatType.STRENGTH);
            Add(SkillId.BREAKING_WHEEL);
        }
    }
