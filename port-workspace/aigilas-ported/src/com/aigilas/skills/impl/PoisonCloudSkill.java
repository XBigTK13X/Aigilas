package com.aigilas.skills.impl;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.entities.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.statuses.*;import com.aigilas.items.*;import com.spx.entities.*;import com.spx.core.*;    public class PoisonCloudSkill  extends  ISkill    {        public PoisonCloudSkill()            { super(SkillId.POISON_CLOUD, AnimationType.CLOUD);            Add(Elements.MENTAL);            AddCost(StatType.MANA, 0);        }@Override        public  void Affect(ICreature target)        {            StatusFactory.Apply(target, Status.Poison);        }    }