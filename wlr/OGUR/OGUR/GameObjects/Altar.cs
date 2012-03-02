using System.Collections.Generic;
using Microsoft.Xna.Framework;
using OGUR.Collision;
using OGUR.Items;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;
using OGUR.Creatures;
using OGUR.Text;
using OGUR.Gods;
using System.Linq;

namespace OGUR.GameObjects
{
    public class Altar : Entity
    {
        private readonly God m_god;
        private Player m_currentTarget;
        private IEnumerable<Entity> m_offerings;

        public Altar(Point2 location,int godName)
        {
            m_god = God.Get(godName);
            m_graphic.SetColor(m_god.GetColor());
            Initialize(location, SpriteType.ALTAR, GameObjectType.ALTAR,Depth.Altar);
        }

        public override void Update()
        {
            m_currentTarget = EntityManager.GetTouchingPlayer(this);
            if (m_currentTarget != null)
            {
                if (m_currentTarget.IsInteracting())
                {
                    m_currentTarget.Pray(m_god);
                }
                m_offerings = EntityManager.GetObjects(GameObjectType.ITEM, m_location);
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