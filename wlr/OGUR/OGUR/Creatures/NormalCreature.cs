using OGUR.Collision;
using OGUR.Strategies;

namespace OGUR.Creatures
{
    class NormalCreature:ICreature
    {
        private readonly Point2 m_position;

        public NormalCreature(CreatureType type,Point2 position,Stats stats)
        {
            base.Setup(position.X, position.Y, type, stats);
            m_position = position;
            m_strategy = new AttackPlayers(this);
        }
    }
}
