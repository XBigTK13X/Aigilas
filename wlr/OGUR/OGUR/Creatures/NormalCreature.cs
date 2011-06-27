using OGUR.Collision;
using OGUR.Strategies;

namespace OGUR.Creatures
{
    class NormalCreature:ICreature
    {
        private readonly Point m_position;

        public NormalCreature(CreatureType type,Point position,Stats stats)
        {
            base.Setup(position.X, position.Y, type, stats);
            m_position = position;
            m_strategy = new AttackPlayers(this);
        }
    }
}
