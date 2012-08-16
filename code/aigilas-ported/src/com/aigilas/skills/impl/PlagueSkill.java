package com.aigilas.skills.impl;import com.aigilas.statuses.StatusFactory;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;import com.aigilas.statuses.Status;    public class PlagueSkill  extends  ISkill    {        public PlagueSkill()            { super(SkillId.STRENGTH_UP, AnimationType.SELF);            Add(Elements.MENTAL);            AddCost(StatType.MANA, 10);        }@Override        public  void Activate(ICreature source)        {            super.Activate(source);            StatusFactory.Apply(source, Status.Toxic);        }    }