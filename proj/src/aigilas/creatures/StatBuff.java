package aigilas.creatures;

public class StatBuff {
    public final StatType Stat;
    public final float Amount;

    public StatBuff(StatType stat, float amount) {
        Stat = stat;
        Amount = amount;
    }
}
