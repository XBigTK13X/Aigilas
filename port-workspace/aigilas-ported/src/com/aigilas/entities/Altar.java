package com.aigilas.entities;import com.xna.wrapper.*;import java.util.*;import com.aigilas.creatures.*;import com.aigilas.gods.*;import com.aigilas.items.*;import com.spx.core.*;import com.spx.entities.*;import com.spx.sprites.*;import com.spx.text.*;import com.aigilas.management.*;
    public class Altar  extends  Entity
    {
        private God _god;
        private Player _currentTarget;
        private List<IEntity> _offerings;

        public Altar(Point2 location,int godName)
        {
            _god = God.Get(godName);
            _graphic.SetColor(_god.GetColor());
            Initialize(location, SpriteType.ALTAR, com.aigilas.EntityType.ALTAR,com.aigilas.Depth.Altar);
        }

@Override        public  void Update()
        {
            _currentTarget = (Player)EntityManager.GetTouchingCreature(this);
            if (_currentTarget != null)
            {
                if (_currentTarget.IsInteracting())
                {
                    _currentTarget.Pray(_god);
                }
                _offerings = EntityManager.GetEntities(com.aigilas.EntityType.ITEM, _location);
                for (IEntity offering:_offerings)
                {
                    _currentTarget.Sacrifice(_god,(GenericItem)offering);
                }
                TextManager.Add(new ActionText(_god.NameText, 1, (int) GetLocation().PosX, (int) GetLocation().PosY));
            }
        }

        public God GetGod()
        {
            return _god;
        }
    }
