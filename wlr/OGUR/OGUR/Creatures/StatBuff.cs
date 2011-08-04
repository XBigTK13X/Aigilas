namespace OGUR.Creatures
{
    public class StatBuff
    {
        public StatType Stat { get; private set; }
        public float Amount { get; private set; }
        public StatBuff(StatType stat, float amount)
        {
            Stat = stat;
            Amount = amount;
        }
    }
}