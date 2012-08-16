package com.aigilas.creatures;import com.xna.wrapper.*;import java.util.*;import com.aigilas.skills.*;import com.spx.sprites.*;import com.aigilas.strategies.*;import com.aigilas.entities.*;import com.spx.entities.*;import com.spx.core.*;
    public class Minion  extends  ICreature
    {
        public Minion(int actorType,float coolDown)
        {
        	Construct(actorType,coolDown);
        }        public Minion(int actorType)        {        	Construct(actorType,Stats.DefaultCoolDown);        }        public Minion()        {            Construct(AigilasActorType.MINION,Stats.DefaultCoolDown);        }        private void Construct(int actorType,float coolDown){        	_actorType = actorType;            _baseStats = new Stats(80f,999f,0f,0f,0f,0f,0f,0f,0f,coolDown);        }
        public void Init(ICreature source,SkillEffect effectGraphic)
        {
            _master = source;
            Setup(source.GetLocation(), _actorType, _baseStats,null,false);
            if (null != effectGraphic)
            {
                SetSkillVector(effectGraphic.GetDirection().Rotate180());
            }
            else
            {
                SetSkillVector(new Point2(0, 1));
            }
            _strategy.AddTargets(_master);
        }
        protected void Add(String skill)
        {
            if (_skills == null)
            {
                _skills = new SkillPool(this);
            }
            _skills.Add(skill);
        }
    }

