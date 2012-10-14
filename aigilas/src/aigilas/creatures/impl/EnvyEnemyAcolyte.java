package aigilas.creatures.impl;

import aigilas.creatures.StatType;
import aigilas.entities.Elements;
import sps.bridge.ActorType;

public class EnvyEnemyAcolyte extends BaseEnemy {
    public EnvyEnemyAcolyte() {
        super(ActorType.Envy_Acolyte);
        weaknesses(StatType.Strength, StatType.Health, StatType.Move_Cool_Down);
        compose(Elements.Water);
    }
}
