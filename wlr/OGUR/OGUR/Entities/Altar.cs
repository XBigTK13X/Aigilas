using System.Collections.Generic;
using OGUR.Creatures;
using OGUR.Gods;
using OGUR.Items;
using SPX.Core;
using SPX.Entities;
using SPX.Sprites;
using SPX.Text;
using OGUR.Management;
using System;
using System.Runtime.Serialization;

namespace OGUR.Entities
{
    [Serializable()]
    public class Altar : Entity
    {
        private readonly God _god;
        private Player _currentTarget;
        private List<IEntity> _offerings;

        public Altar(SerializationInfo info, StreamingContext context):base(info, context){}
        public Altar(Point2 location,int godName)
        {
            _god = God.Get(godName);
            _graphic.SetColor(_god.GetColor());
            Initialize(location, SpriteType.ALTAR, OGUR.EntityType.ALTAR,ZDepth.Altar);
        }

        public override void Update()
        {
            _currentTarget = EntityManager.GetTouchingCreature(this) as Player;
            if (_currentTarget != null)
            {
                if (_currentTarget.IsInteracting())
                {
                    _currentTarget.Pray(_god);
                }
                _offerings = EntityManager.GetEntities(OGUR.EntityType.ITEM, _location);
                foreach (GenericItem offering in _offerings)
                {
                    _currentTarget.Sacrifice(_god, offering);
                }
                TextManager.Add(new ActionText(_god.NameText, 1, (int) GetLocation().PosX, (int) GetLocation().PosY));
            }
        }

        public God GetGod()
        {
            return _god;
        }
    }
}