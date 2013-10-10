package aigilas.creatures;

public class StatBuff {
    public final StatType Stat;
    public final int Amount;

    public StatBuff(StatType stat, int amount) {
        Stat = stat;
        Amount = amount;
    }
}
