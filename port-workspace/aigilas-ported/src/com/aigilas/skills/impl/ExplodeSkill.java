package com.aigilas.skills.impl;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.entities.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.statuses.*;import com.aigilas.items.*;import com.spx.entities.*;import com.spx.core.*;    public class ExplodeSkill  extends  ISkill    {        public ExplodeSkill()            { super(SkillId.EXPLODE, AnimationType.CLOUD);            Add(Elements.FIRE);            AddCost(StatType.MANA, 0);        }@Override        public  void Affect(ICreature target)        {            target.ApplyDamage(10, _source, true);        }    }