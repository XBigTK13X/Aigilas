using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using OGUR.Skills;
using OGUR.Strategies;
using OGUR.Classes;
using OGUR.Sprites;
using Microsoft.Xna.Framework;

namespace OGUR.Creatures
{
    public class Player : AbstractCreature
    {
        private static readonly List<Color> s_colors = new List<Color>() { Color.Red,Color.Green,Color.Blue,Color.White};
        public Player(int playerIndex)
            : base(CreatureType.PLAYER, new Stats(100f, 100f, 10f, 10f, 10f, 10f, 35f, 50f, 6.0f,Stats.DefaultMoveSpeed,6), SpriteType.PLAYER_STAND, new SlothAcolyte())
        {
            m_playerIndex = playerIndex;
            m_graphic.SetColor(s_colors[m_playerIndex]);
            m_strategy = new ControlledByPlayer(this);
        }
    }
    class Goblin : AbstractCreature
    {
        public Goblin()
            : base(CreatureType.GOBLIN, new Stats(50f, 10f, 1f, 1f, 1f, 20f, 1f, 10f, 1.5f))
        { }
    }
    class Zorb : AbstractCreature
    {
        public Zorb()
            : base(CreatureType.ZORB, new Stats(50f, 50f, 10f, 10f, 10f, 20f, 10f, 10f, 1.5f))
        {
            Add(SkillId.FIREBALL);
        }
    }
}
