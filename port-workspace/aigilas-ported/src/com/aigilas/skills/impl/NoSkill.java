package com.aigilas.skills.impl;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.entities.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.statuses.*;import com.aigilas.items.*;import com.spx.entities.*;import com.spx.core.*;    public class NoSkill  extends  ISkill    {        public NoSkill()            { super(SkillId.NO_SKILL, AnimationType.NONE);        }@Override        public  void Activate(ICreature source)        {        }@Override        public  void Affect(IEntity target)        {        }@Override        public  void Affect(ICreature target)        {        }    }