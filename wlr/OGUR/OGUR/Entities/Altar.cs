using System.Collections.Generic;
using OGUR.Creatures;
using OGUR.Gods;
using OGUR.Items;
using SPX.Core;
using SPX.Entities;
using SPX.Sprites;
using SPX.Text;

namespace OGUR.Entities
{
    public class Altar : Entity
    {
        private readonly God m_god;
        private Player m_currentTarget;
        private List<IEntity> m_offerings;

        public Altar(Point2 location,int godName)
        {
            m_god = God.Get(godName);
            m_graphic.SetColor(m_god.GetColor());
            Initialize(location, SpriteType.ALTAR, OGUR.EntityType.ALTAR,ZDepth.Altar);
        }

        public override void Update()
        {
            m_currentTarget = EntityManager.GetTouchingCreature(this) as Player;
            if (m_currentTarget != null)
            {
                if (m_currentTarget.IsInteracting())
                {
                    m_currentTarget.Pray(m_god);
                }
                m_offerings = EntityManager.GetEntities(OGUR.EntityType.ITEM, m_location);
                foreach (GenericItem offering in m_offerings)
                {
                    m_currentTarget.Sacrifice(m_god, offering);
                }
                TextManager.Add(new ActionText(m_god.NameText, 1, (int) GetLocation().PosX, (int) GetLocation().PosY));
            }
        }

        public God GetGod()
        {
            return m_god;
        }
    }
}