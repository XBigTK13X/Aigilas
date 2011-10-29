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
    public class Altar : GameplayObject
    {
        private readonly God m_god;

        public Altar(Point2 location, God.Name godName)
        {
            m_god = God.Get(godName);
            m_graphic.SetColor(m_god.GetColor());
            Initialize(location, SpriteType.ALTAR, GameObjectType.ALTAR);
        }

        public override void Update()
        {
            var player = GameplayObjectManager.GetTouchingPlayer(this);
            if (player != null)
            {
                if (player.IsInteracting())
                {
                    player.Pray(m_god);
                }
                var offerings = GameplayObjectManager.GetObjects(GameObjectType.ITEM, m_location);
                foreach (GenericItem offering in offerings)
                {
                    player.Sacrifice(m_god, offering);
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