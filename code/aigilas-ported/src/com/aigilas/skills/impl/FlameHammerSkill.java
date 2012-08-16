package com.aigilas.skills.impl;import com.aigilas.statuses.StatusFactory;import com.aigilas.creatures.ICreature;import com.aigilas.creatures.StatType;import com.aigilas.entities.Elements;import com.aigilas.skills.AnimationType;import com.aigilas.skills.ISkill;import com.aigilas.skills.SkillId;    public class FlameHammerSkill  extends  ISkill    {        public FlameHammerSkill()            { super(SkillId.FLAME_HAMMER, AnimationType.ROTATE);            Add(Elements.FIRE);            AddCost(StatType.MANA, 10);        }@Override        public  void Affect(ICreature target)        {            target.ApplyDamage(3f, _source);        }    }