package com.aigilas.reactions.impl;import com.aigilas.statuses.StatusFactory;import com.aigilas.creatures.ICreature;import com.aigilas.reactions.IReaction;import com.aigilas.statuses.Status;    public class YinYangReaction  implements IReaction    {        @Override		public void Affect(ICreature target)        {            StatusFactory.Apply(target, Status.SelfMutilation);        }    }