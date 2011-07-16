using System.Collections.Generic;
using Microsoft.Xna.Framework;
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
        private God m_god;

        public Altar(int x, int y, God.Name godName)
        {
            m_god = God.Get(godName);
            m_graphic.SetColor(m_god.GetColor());
            Initialize(x, y, SpriteType.ALTAR, GameObjectType.ALTAR);
        }

        public override void Update()
        {
            var offerings = GameplayObjectManager.GetObjects(GameObjectType.ITEM).Where(o => Collision.HitTest.IsTouching(this, o)).Cast<GenericItem>().ToList();
            var player = (Player)GameplayObjectManager.GetNearestPlayer(this);
            if (Collision.HitTest.IsTouching(this,player))
            {
                foreach(var offering in offerings)
                {
                    m_god.AcceptSacrifice(player,offering);
                }
                TextManager.Add(new ActionText(m_god.ToString(), 1, (int) this.GetPosition().X, (int) this.GetPosition().Y));
            }
        }
    }
}