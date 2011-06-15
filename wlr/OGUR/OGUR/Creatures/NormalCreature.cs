using OGUR.Collision;

namespace OGUR.Creatures
{
    class NormalCreature:ICreature
    {
        private readonly Point m_position;

        public NormalCreature(CreatureType type,Point position,Stats stats)
        {
            m_position = position;
        }
    }
}
