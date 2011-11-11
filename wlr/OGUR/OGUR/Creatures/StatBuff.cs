namespace OGUR.Creatures
{
    public class StatBuff
    {
        public string Stat { get; private set; }
        public float Amount { get; private set; }
        public StatBuff(string stat, float amount)
        {
            Stat = stat;
            Amount = amount;
        }
    }
}