package com.aigilas.skills.impl;import com.aigilas.statuses.StatusFactory;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;    public class ValedictorianSkill  extends  ISkill    {        public ValedictorianSkill()            { super(SkillId.VALEDICTORIAN, AnimationType.RANGED);            Add(Elements.MENTAL, Elements.LIGHT);            AddCost(StatType.MANA, 10);        }@Override        public  void Affect(ICreature target)        {        }    }