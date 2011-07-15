using Microsoft.Xna.Framework;
using OGUR.Sprites;
using OGUR.Management;
using OGUR.Dungeons;
using OGUR.Creatures;
using OGUR.Text;

namespace OGUR.GameObjects
{
    public enum GodName
    {
        LUST,
        GREED,
        SLOTH,
        ENVY,
        WRATH, 
        GLUTTONY,
        PRIDE
    }

    public class Altar : GameplayObject
    {
        public static Color ToColor(GodName god)
        {
            switch(god)
            {
                case GodName.ENVY:
                    return Color.Green;
                case GodName.GREED:
                    return Color.Gold;
                case GodName.GLUTTONY:
                    return Color.YellowGreen;
                case GodName.LUST:
                    return Color.Pink;
                case GodName.PRIDE:
                    return Color.Indigo;
                case GodName.SLOTH:
                    return Color.Black;
                case GodName.WRATH:
                    return Color.Red;
                default:
                    return Color.White;
            }
        }
        private GodName m_god;
        public Altar(int x, int y, GodName god)
        {
            m_god = god;
            m_graphic.SetColor(ToColor(god));
            Initialize(x, y, SpriteType.ALTAR, GameObjectType.ALTAR);
        }
        public override void Update()
        {
            if(Collision.HitTest.IsTouching(this,GameplayObjectManager.GetNearestPlayer(this)))
            {
                TextManager.Add(new ActionText(m_god.ToString(),1,(int)this.GetPosition().X,(int)this.GetPosition().Y));
            }
        }
    }
}