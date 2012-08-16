package com.aigilas.skills.impl;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.entities.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.statuses.*;import com.aigilas.items.*;import com.spx.entities.*;import com.spx.core.*;    public class ThrowItemSkill  extends  ISkill    {        private float _itemStrength = 0;        public ThrowItemSkill()            { super(SkillId.THROW_ITEM, AnimationType.RANGED);            Add(Elements.AIR);            AddCost(StatType.MANA, 0);        }@Override        public  void Activate(ICreature source)        {            var item = source.DestroyRandomItemFromInventory();            if (item != null)            {                _itemStrength = item.Modifers.GetSum() * 3;                super.Activate(source);            }        }@Override        public  void Affect(ICreature target)        {            target.ApplyDamage(_itemStrength, _source);        }    }